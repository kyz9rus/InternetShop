package ru.tsystems.internetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.ClientDto;


@Controller("/client")
public class MainController {

    @PostMapping(value = "create-client")
    public ModelAndView createClient(@Validated @ModelAttribute("client") ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("client", clientDto);

        modelAndView.setViewName("registration");

        System.out.println(clientDto);

        return modelAndView;
    }

    @PostMapping(value = "change-password")
    public ModelAndView changePassword(@ModelAttribute("clientDto") ClientDto clientDto) {
        ModelAndView modelAndView = new ModelAndView();

//        достать из бд, сравнить пароли, если совпадают то изменить

        modelAndView.addObject("clientDto", clientDto);

        modelAndView.addObject("successMessage", "Пароль успешно изменен");

        modelAndView.setViewName("clientProfile");

        return modelAndView;
    }
}
