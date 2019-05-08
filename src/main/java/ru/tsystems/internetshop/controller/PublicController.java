package ru.tsystems.internetshop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
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
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.service.*;
import ru.tsystems.internetshop.util.CategoryInfo;
import ru.tsystems.internetshop.util.NewsInfo;
import ru.tsystems.internetshop.util.ResponseInfo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes(value = {"client", "basket"})
public class PublicController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private NewsInfo newsInfo;

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

    private final Logger consoleLogger = Logger.getLogger("consoleLogger");
    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @GetMapping("exception")
    public String toExceptionPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "exception";
    }

    @GetMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("client", authenticationService.getClient());
        model.addAttribute("clients", clientService.getTop10Clients());
        model.addAttribute("newsList", newsInfo.getNews().stream().sorted(Comparator.comparing(NewsDTO::getWritingDate).reversed()).limit(7).collect(Collectors.toList()));
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

    @PostMapping(value = "client")
    public String createClient(@Validated @ModelAttribute("client") ClientDTO client, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        consoleLogger.info("Register client: " + client + "...");
        fileLogger.info("Register client: " + client + "...");

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
        model.addAttribute("categories", categoryInfo.getCategories());
        return "category";
    }

    @PostMapping(value = "put-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<BasketInfo> putProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        consoleLogger.info("Put product with id " + productId + " in basket + " + basket + "...");
        fileLogger.info("Put product with id " + productId + " in basket + " + basket + "...");

        ProductDTO productDTO = productService.getProduct(productId);
        basket.addProduct(productDTO);

        if (basket.getCouponDTO() != null) {
            basket.setChangedAfterCoupon(true);

            basket.setSummaryPrice(basketService.calcPriceWithoutDiscount(basket));
        }

        model.addAttribute("basket", basket);

        return new ResponseEntity<>(new BasketInfo(basket, basket.getProducts().get(productDTO)), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "increase-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasketInfo> increaseProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        consoleLogger.info("Delete 1 product with id " + productId + " from basket " + basket + "...");
        fileLogger.info("Delete 1 product with id " + productId + " from basket " + basket + "...");

        ProductDTO productDTO = productService.getProduct(productId);
        basket.increaseProduct(productDTO);

        model.addAttribute("basket", basket);

        return new ResponseEntity<>(new BasketInfo(basket, basket.getProducts().get(productDTO)), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "remove-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BasketInfo> addProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        consoleLogger.info("Remove products with id " + productId + " from basket " + basket + "...");
        fileLogger.info("Remove products with id " + productId + " from basket " + basket + "...");

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
    ResponseInfo sendCoupon(@ModelAttribute("client") ClientDTO clientDTO, @RequestParam("couponName") String couponName) throws MailException {
        consoleLogger.info("Sending coupon to + " + clientDTO.getEmail() +  "...");
        fileLogger.info("Sending coupon to + " + clientDTO.getEmail() +  "...");

        String email = clientDTO.getEmail();
        ResponseInfo responseInfo;

        CouponDTO couponDTO = couponService.getCouponByValue(couponName);

        if (couponDTO != null) {
            if (clientDTO.getCoupons().contains(couponDTO))
                responseInfo = new ResponseInfo("You have already used this coupon.", 404);
            else {
                try {
                    mailService.sendNewCouponLetter(email, couponDTO);
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

    @GetMapping(value = "top10Products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ProductDTO> getTop10Products() {
        return productService.getTop10Products();
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
