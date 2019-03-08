package ru.tsystems.internetshop.controller;

import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ClientDto;
import ru.tsystems.internetshop.model.ProductDto;
import ru.tsystems.internetshop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;


//@Controller("/client")
@Controller
public class PublicController {

    @Autowired
    private ClientService clientService;

    @PostMapping(value = "create-client")
    public ModelAndView createClient(@Validated @ModelAttribute("client") ClientDto clientDto, @RequestParam("repeatPassword") String repeatPassword) {
        ModelAndView modelAndView = new ModelAndView();

        // если пользователь не найден, то
        if (clientDto.getPassword().equals(repeatPassword)) {
            clientService.saveClient(clientDto);

            modelAndView.addObject("successMessage", "You have been successfully registered. Sign in!");

//                modelAndView.addObject("errorMessage", "Something wrong...");
        } else {
            modelAndView.addObject("errorMessage", "Entered passwords do not match");
        }
        // иначе modelAndView.addObject("formMessage", "A user with this email address already exists.");

        modelAndView.addObject("client", clientDto);

        modelAndView.setViewName("registration");

        System.out.println(clientDto);

        return modelAndView;
    }

    @GetMapping(value = "category/{categoryName}")
    public ModelAndView getCategory(@PathVariable("categoryName") String categoryName) {
        ModelAndView modelAndView = new ModelAndView();

        List<ProductDto> products = new ArrayList<>();

        products.add(new ProductDto("Fragrances №1", 345, new Category(categoryName), null, 40, "40x20x10", 100L));
        products.add(new ProductDto("Fragrances №2", 345, new Category(categoryName), null, 40, "40x20x10", 100L));
        products.add(new ProductDto("Fragrances №3", 345, new Category(categoryName), null, 40, "40x20x10", 100L));
//        List<ProductDto> products = productService.findByCategory();

        modelAndView.addObject("categoryName", categoryName.toUpperCase());

        modelAndView.addObject("products", products);

        modelAndView.setViewName("category");

        return modelAndView;
    }
}