package ru.tsystems.internetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.ClientDto;


@Controller
public class MappingController {

    @GetMapping(value = "/")
    public String main() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("clientDtoJSP", new ClientDto());
//        modelAndView.setViewName("index");
//        return modelAndView;
        return "index";
    }

    @GetMapping(value = "login")
    public String toLoginPage() {
        return "login";
    }
}
