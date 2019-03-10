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


import java.util.ArrayList;
import java.util.List;


//@Controller("/client")
@Controller
public class PublicController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "create-client")
    public ModelAndView createClient(@Validated @ModelAttribute("client") ClientDto clientDto, @RequestParam("repeatPassword") String repeatPassword) {
        System.out.println(clientDto);

        ModelAndView modelAndView = new ModelAndView();

        if (clientService.getClientByEmail(clientDto.getEmail()) != null)
            modelAndView.addObject("errorMessage", "A user with this email address already exists.");
        else {
            if (clientDto.getPassword().equals(repeatPassword)) {
                clientService.saveClient(clientDto);

                modelAndView.addObject("successMessage", "You have been successfully registered. Sign in!");
            } else {
                modelAndView.addObject("errorMessage", "Entered passwords do not match.");
            }
        }

        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @PostMapping(value = "update-client")
    public ModelAndView updateClient(@Validated @ModelAttribute("client") ClientDto clientDto) {
        System.out.println(clientDto);

        ModelAndView modelAndView = new ModelAndView();

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

        modelAndView.setViewName("clientProfile");

        return modelAndView;
    }

    @GetMapping(value = "category/{categoryName}")
    public String getCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<ProductDto> products = productService.getProductsByCategory(new Category(categoryName));

        products.add(new ProductDto("Fragrances №1", 345, 40, "40x20x10", 100L));
        products.add(new ProductDto("Fragrances №2", 345, 40, "40x20x10", 200L));
        products.add(new ProductDto("Fragrances №3", 345, 2340, "999x999x999", 100L));

        if (!products.isEmpty())
            model.addAttribute("products", products);
        else
            model.addAttribute("emptyListMessage", "Product list is empty.");

        model.addAttribute("categoryName", categoryName.replaceAll("_", " ").toUpperCase());

        model.addAttribute("products", products);

        return "category";
    }
}
