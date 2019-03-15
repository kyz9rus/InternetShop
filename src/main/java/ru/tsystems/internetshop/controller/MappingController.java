package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes(value = "client")
public class MappingController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryInfo categoryInfo;

    @GetMapping(value = "/")
    public String main(Model model) {
        List<Category> categories = categoryService.getAllCategories();

        categories.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

        categoryInfo.getInstance().clear();
        categoryInfo.getInstance().addAll(categories);

        model.addAttribute("categories", categoryInfo.getInstance());

        model.addAttribute("client", createClient());
        return "index";
    }

    @GetMapping(value = "registration")
    public String toRegistrationPage(Model model) {
        model.addAttribute("client", new ClientDTO());
        model.addAttribute("categories", categoryInfo.getInstance());
        return "registration";
    }

    @ModelAttribute("client")
    public ClientDTO createClient() {
        return new ClientDTO();
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping(value = "clientProfile")
    public String toClientProfile(Model model) {
//        model.addAttribute("client", new Client("Daniil", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));
        if(!model.containsAttribute("client"))
            model.addAttribute("client", createClient());

        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "employeeProfile")
    public String toEmployeeProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        return "employeeProfile";
    }
}
