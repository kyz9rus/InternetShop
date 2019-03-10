package ru.tsystems.internetshop.controller;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ClientDto;
import ru.tsystems.internetshop.model.ProductDto;
import ru.tsystems.internetshop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.CategoryInfo;


import java.util.ArrayList;
import java.util.List;

@Controller
public class PublicController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryInfo categoryInfo;

    @PostMapping(value = "create-client")
    public String createClient(@Validated @ModelAttribute("client") ClientDto clientDto, @RequestParam("repeatPassword") String repeatPassword, Model model) {
        if (clientService.getClientByEmail(clientDto.getEmail()) != null)
            model.addAttribute("errorMessage", "A user with this email address already exists.");
        else {
            if (clientDto.getPassword().equals(repeatPassword)) {
                clientService.saveClient(clientDto);

                model.addAttribute("successMessage", "You have been successfully registered. Sign in!");
            } else {
                model.addAttribute("errorMessage", "Entered passwords do not match.");
            }
        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "registration";
    }

    @PostMapping(value = "update-client")
    public String updateClient(@Validated @ModelAttribute("client") ClientDto clientDto, Model model) {
        System.out.println(clientDto);

        // add SPRING SESSION
//        if (clientService.getClientByEmail(clientDto.getEmail()) != null)
//            modelAndView.addObject("errorMessage", "A user with this email address already exists.");
//        else {
//            if (clientDto.getPassword().equals(repeatPassword)) {
//                clientService.saveClient(clientDto);
//
//                modelAndView.addObject("successMessage", "You have been successfully registered. Sign in!");
//            } else {
//                modelAndView.addObject("errorMessage", "Entered passwords do not match.");
//            }
//        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile";
    }

    @GetMapping(value = "category/{categoryName}")
    public String getCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<ProductDto> products = productService.getProductsByCategory(new Category(categoryName));

        if (!products.isEmpty())
            model.addAttribute("products", products);
        else
            model.addAttribute("emptyListMessage", "Product list is empty.");

        model.addAttribute("categoryName", categoryName.replaceAll("_", " ").toUpperCase());
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "category";
    }
}
