package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.internetshop.util.CategoryInfo;

@Controller
public class ClientController {
    @Autowired
    private CategoryInfo categoryInfo;

    @PostMapping(value = "change-password")
    public String changePassword(@RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("repeatNewPassword") String repeatNewPassword, Model model) {

        // add spring session
        // сравнить пароль пользователя с password, если совпадают
        if (newPassword.equals(repeatNewPassword)) {
            // изменить пароль в бд
            model.addAttribute("successMessage", "Password successfully changed");
        } else {
            model.addAttribute("errorMessage", "Entered passwords do not match");
        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile";
    }
}
