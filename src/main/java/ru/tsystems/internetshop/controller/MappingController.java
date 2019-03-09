package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ClientDto;
import ru.tsystems.internetshop.model.ClientListContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.service.CategoryService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MappingController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/")
    public String main(Model model) {
        List<ClientDto> clients = new ArrayList<>();
        clients.add(new ClientDto("Daniil", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));
        clients.add(new ClientDto("Daniil2", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));

        model.addAttribute("clients", clients);
        return "index";
    }

    @GetMapping(value = "registration")
    public String toLoginPage(Model model) {
        model.addAttribute("client", new ClientDto());
        return "registration";
    }

    @GetMapping(value = "clientProfile")
    public ModelAndView toClientProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("clientDto", new ClientDto("Daniil", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));
        modelAndView.setViewName("clientProfile");
        return modelAndView;
    }

    @GetMapping(value = "employeeProfile")
    public ModelAndView toEmployeeProfile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");

//        modelAndView.addObject("categories", categoryService.getAllCategories());

        List<Category> categories = new ArrayList<>();
        categories.addAll(Arrays.asList(new Category("fragrances"), new Category("for face")));

        modelAndView.addObject("categories", categories);
        return modelAndView;
    }
}
