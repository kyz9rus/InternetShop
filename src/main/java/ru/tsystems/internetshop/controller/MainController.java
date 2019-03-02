package ru.tsystems.internetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.ClientDto;


@Controller("/client")
public class MainController {

    @PostMapping(value = "create-client")
    public ModelAndView createClient(@Validated @ModelAttribute("clientDtoJSP") ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("clientDtoJSP", clientDto);

        modelAndView.setViewName("secondPage");

        return modelAndView;
    }

    @PostMapping(value = "change-password")
    public ModelAndView changePassword(@ModelAttribute("clientDtoJSP") ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();

//        достать из бд, сравнить пароли, если совпадают то изменить

        modelAndView.addObject("clientDtoJSP", clientDto);

        modelAndView.setViewName("secondPage");

        return modelAndView;
    }
}
