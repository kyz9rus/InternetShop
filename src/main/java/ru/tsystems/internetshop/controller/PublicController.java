package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes(value = {"client", "basket"})
public class PublicController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private UserClientFacade userClientFacade;

    @Autowired
    private ProductService productService;

    @GetMapping("exception")
    public String toExceptionPage() {
        return "exception";
    }

    @GetMapping(value = "/")
    public String main(@ModelAttribute("basket") Basket basket, Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();

        categoryInfo.getInstance().clear();
        categoryInfo.getInstance().addAll(categories);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "index";
    }

    @GetMapping(value = "registration")
    public String toRegistrationPage(@ModelAttribute("basket") Basket basket, Model model) {
        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());
        return "registration";
    }

    @GetMapping(value = "login")
    public String toLoginPage(@ModelAttribute("basket") Basket basket, Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        model.addAttribute("basket", basket);
        return "login";
    }

    @PostMapping(value = "create-client")
    public String createClient(@ModelAttribute("basket") Basket basket, @Validated @ModelAttribute("client") ClientDTO client, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
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

        model.addAttribute("categories", categoryInfo.getInstance());

        return "registration";
    }

    @GetMapping(value = "category/{categoryName}")
    public String getCategory(@ModelAttribute("basket") Basket basket, @PathVariable("categoryName") String categoryName, Model model) {
        List<ProductDTO> products = productService.getProductsByCategory(new CategoryDTO(categoryName));

        if (!products.isEmpty())
            model.addAttribute("products", products);
        else
            model.addAttribute("emptyListMessage", "Product list is empty.");

        model.addAttribute("categoryName", categoryName.replaceAll("_", " ").toUpperCase());
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryInfo.getInstance());
        model.addAttribute("basket", basket);

        return "category";
    }

    @PostMapping(value = "put-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Basket> putProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.addProduct(productDTO);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "increase-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Basket> increaseProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.increaseProduct(productDTO);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "remove-product", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Basket> addProduct(@RequestParam("productId") Long productId, @ModelAttribute("basket") Basket basket, Model model) {
        ProductDTO productDTO = productService.getProduct(productId);
        basket.removeProduct(productDTO);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());

        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping(value = "clientProfile")
    public String toClientProfile(@ModelAttribute("basket") Basket basket, Model model) {
        Authentication authentication = getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty())
            for (GrantedAuthority grantedAuthority : authorities)
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENT")) {
                    UserDTO userDTO = (UserDTO) authentication.getPrincipal();

                    ClientDTO clientDTO = clientService.getClientByEmail(userDTO.getEmail());

                    model.addAttribute("client", clientDTO);
                }

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "employeeProfile")
    public String toEmployeeProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile";
    }

    @ModelAttribute("client")
    public ClientDTO createClient() {
        return new ClientDTO();
    }

    @ModelAttribute("basket")
    public Basket createBasket() {
        return new Basket();
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("logout")
    public ModelAndView logout(Model model) {
        return new ModelAndView("redirect:" + "/logout");
    }
}
