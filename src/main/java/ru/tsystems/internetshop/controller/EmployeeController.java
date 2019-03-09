package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.ProductDto;
import ru.tsystems.internetshop.service.ProductService;

@Controller
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @PostMapping("create-product")
    public ModelAndView createProduct(@Validated @ModelAttribute("product") ProductDto product){
        System.out.println(product);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");

        if (productService.getProductByName(product.getName()) == null) {
            productService.saveProduct(product);

            modelAndView.addObject("successMessage", "Product saved successfully!");
        } else
            modelAndView.addObject("errorMessage", "A product with this name already exists.");

        return modelAndView;
    }

}
