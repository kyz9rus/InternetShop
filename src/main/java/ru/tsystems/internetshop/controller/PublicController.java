package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes(value = {"client", "authenticationRole"})
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

    @GetMapping(value = "/")
    public String main(Model model) {
        List<CategoryDTO> categories = categoryService.getAllCategories();

        categories.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

        categoryInfo.getInstance().clear();
        categoryInfo.getInstance().addAll(categories);

        model.addAttribute("categories", categoryInfo.getInstance());

        if (!model.containsAttribute("client"))
            model.addAttribute("client", createClient());

        return "index";
    }

    @GetMapping(value = "registration")
    public String toRegistrationPage(Model model) {
        model.addAttribute("client", new ClientDTO());
        model.addAttribute("categories", categoryInfo.getInstance());
        return "registration";
    }

    @PostMapping(value = "create-client")
    public String createClient(@Validated @ModelAttribute("client") ClientDTO client, @RequestParam("password") String password, @RequestParam("repeatPassword") String repeatPassword, Model model) {
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
    public String getCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<Product> products = productService.getProductsByCategory(new Category(categoryName));

        if (!products.isEmpty())
            model.addAttribute("products", products);
        else
            model.addAttribute("emptyListMessage", "Product list is empty.");

        model.addAttribute("categoryName", categoryName.replaceAll("_", " ").toUpperCase());
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "category";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping(value = "clientProfile")
    public String toClientProfile(Model model) {
        Authentication authentication = getAuthentication();

        if (getAuthenticationRole().equals("ROLE_CLIENT")) {
            UserDTO userDTO = (UserDTO) authentication.getPrincipal();

            ClientDTO clientDTO = clientService.getClientByEmail(userDTO.getEmail());

            model.addAttribute("client", clientDTO);
            model.addAttribute("authenticationRole", "ROLE_CLIENT");
        }

        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "employeeProfile")
    public String toEmployeeProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        model.addAttribute("authenticationRole", "ROLE_EMPLOYEE");
        return "employeeProfile";
    }

    @ModelAttribute("client")
    public ClientDTO createClient() {
        return new ClientDTO();
    }

    @ModelAttribute("authenticationRole")
    public String getAuthenticationRole() {
        Authentication authentication = getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty())
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENT"))
                    return "ROLE_CLIENT";
                else if (grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE"))
                    return "ROLE_EMPLOYEE";
            }
        return "AnonymousUser";
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("logout")
    public ModelAndView logout(Model model) {
        model.addAttribute("client", createClient());
//        model.addAttribute("basket", createBasket());
        return new ModelAndView("redirect:" + "/logout");
    }
}
