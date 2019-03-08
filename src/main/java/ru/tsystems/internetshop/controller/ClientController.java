package ru.tsystems.internetshop.controller;

import ru.tsystems.internetshop.model.ClientDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
    @PostMapping(value = "change-password")
    public ModelAndView changePassword(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("repeatNewPassword") String repeatNewPassword) {
        ModelAndView modelAndView = new ModelAndView();

        // сравнить пароль пользователя с password, если совпадают
        if (newPassword.equals(repeatNewPassword)) {
            // изменить пароль в бд
            modelAndView.addObject("successMessage", "Password successfully changed");
        } else {
            modelAndView.addObject("errorMessage", "Entered passwords do not match");
        }

        modelAndView.addObject("clientDto", new ClientDto());

        modelAndView.setViewName("clientProfile");

        return modelAndView;
    }
}
