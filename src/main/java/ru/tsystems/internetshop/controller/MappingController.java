package ru.tsystems.internetshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.ClientDto;

import java.time.LocalDate;


@Controller
public class MappingController {

    @GetMapping(value = "/")
    public String main() {
        return "index";
    }

    @GetMapping(value = "login")
    public String toLoginPage() {
        return "login";
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
        return modelAndView;
    }
}
