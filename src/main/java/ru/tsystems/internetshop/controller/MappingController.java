package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.time.LocalDate;
import java.util.List;

@Controller
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
        return "index";
    }

    @GetMapping(value = "registration")
    public String toRegistrationPage(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("categories", categoryInfo.getInstance());
        return "registration";
    }

    @GetMapping(value = "clientProfile")
    public String toClientProfile(Model model) {
        model.addAttribute("clientDto", new Client("Daniil", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));
        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile";
    }

    @GetMapping(value = "employeeProfile")
    public String toEmployeeProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        return "employeeProfile";
    }
}
