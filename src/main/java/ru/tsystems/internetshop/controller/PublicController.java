package ru.tsystems.internetshop.controller;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.BasketInfo;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.CouponDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.service.*;
import ru.tsystems.internetshop.util.CategoryInfo;
import ru.tsystems.internetshop.util.ResponseInfo;

import java.util.List;
import java.util.logging.Logger;

@Controller
@SessionAttributes(value = {"client", "basket"})
public class PublicController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private UserClientFacade userClientFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private CouponService couponService;

    @Autowired
    private MailService mailService;

    @Autowired
    private BasketService basketService;

    private Logger logger = Logger.getLogger("logger");

    @GetMapping("exception")
    public String toExceptionPage() {
        return "exception";
    }

    @GetMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("client", authenticationService.getClient());
        model.addAttribute("products", productService.getTop10Products());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "index";
    }

    @GetMapping(value = "registration")
    public String toRegistrationPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "registration";
    }

    @GetMapping(value = "login")
    public String toLoginPage() {
        return "login";
    }

    @PostMapping(value = "create-client")
    public String createClient(@Validated @ModelAttribute("client") ClientDTO client, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        logger.info("Register client: " + client + "...");

        if (clientService.getClientByEmail(client.getEmail()) != null)
            model.addAttribute("errorMessage", "A user with this email address already exists.");
        else {
            if (password.equals(repeatPassword)) {
                password = (new BCryptPasswordEncoder().encode(password));
                userClientFacade.registerUser(client, password);

                model.addAttribute("successMessage", "You have been successfully registered. Sign in!");
            } else {
                model.addAttribute("errorMessage", "Entered passwords do not match.");
            }
        }

        model.addAttribute("categories", categoryInfo.getCategories());

        return "registration";
    }

    @GetMapping(value = "category/{categoryName}")
    public String getCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<ProductDTO> products = productService.getProductsByCategory(new CategoryDTO(categoryName));

        if (!products.isEmpty())
            model.addAttribute("products", products);
        else
            model.addAttribute("emptyListMessage", "Product list is empty.");

        model.addAttribute("categoryName", categoryName.replaceAll("_", " ").toUpperCase());
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryInfo.getCategories());

        return "category";
    }

    @PostMapping(value = "put-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<BasketInfo> putProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        logger.info("Put product with id " + productId + " in basket + " + basket + "...");

        ProductDTO productDTO = productService.getProduct(productId);
        basket.addProduct(productDTO);

        if (basket.getCouponDTO() != null) {
            basket.setChangedAfterCoupon(true);

            if (basket.getCouponDTO().getName().equals("FIRST_ORDER"))
                basket.setSummaryPrice(Math.round(basketService.calcPrice(basket)));
        }

        model.addAttribute("basket", basket);

        return new ResponseEntity<>(new BasketInfo(basket, basket.getProducts().get(productDTO)), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "increase-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasketInfo> increaseProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        logger.info("Delete 1 product with id " + productId + " from basket " + basket + "...");

        ProductDTO productDTO = productService.getProduct(productId);
        basket.increaseProduct(productDTO);

        model.addAttribute("basket", basket);

        return new ResponseEntity<>(new BasketInfo(basket, basket.getProducts().get(productDTO)), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "remove-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasketInfo> addProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        logger.info("Remove products with id " + productId + " from basket " + basket + "...");

        ProductDTO productDTO = productService.getProduct(productId);
        basket.removeProduct(productDTO);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getCategories());

        return new ResponseEntity<>(new BasketInfo(basket, basket.getProducts().get(productDTO)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping(value = "clientProfile")
    public String toClientProfile(Model model) {
        model.addAttribute("client", authenticationService.getClient());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "employeeProfile")
    public String toEmployeeProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "send-coupon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseInfo sendCoupon(@ModelAttribute("client") ClientDTO clientDTO) {
        logger.info("Sending coupon to + " + clientDTO.getEmail() +  "...");

        String email = clientDTO.getEmail();
        ResponseInfo responseInfo;

        CouponDTO couponDTO = couponService.getCouponByValue("HAPPY_ORDER");

        if (couponDTO != null) {
            if (clientDTO.getCoupons().contains(couponDTO))
                responseInfo = new ResponseInfo("You have already used this coupon.", 404);
            else {
                try {
                    mailService.sendLetter(email, couponDTO);
                    responseInfo = new ResponseInfo("Сoupon successfully sent.\nCheck your email.", 200);
                } catch (SMTPSendFailedException e) {
                    responseInfo = new ResponseInfo("Incorrect email. Change it in your profile!", 404);
                }
            }
        } else {
            responseInfo = new ResponseInfo("Coupon is not available.", 404);
        }

        return responseInfo;
    }

    @ModelAttribute("client")
    public ClientDTO createClient() {
        return new ClientDTO();
    }

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    @GetMapping("logout")
    public ModelAndView logout() {
        return new ModelAndView("redirect:" + "/logout");
    }
}
