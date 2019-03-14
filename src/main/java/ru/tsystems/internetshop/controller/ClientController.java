package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.time.LocalDate;

@Controller
@RequestMapping("clientProfile")
public class ClientController {
    @Autowired
    private CategoryInfo categoryInfo;

    @GetMapping("editProfile")
    public String editProfile(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/editProfile";
    }

    @GetMapping("changePassword")
    public String changePasswordPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/changePassword";
    }

    @GetMapping("issueOrder")
    public String issueOrderPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/issueOrder";
    }

    @GetMapping("showOrderHistory")
    public String showOrderHistoryPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/showOrderHistory";
    }

    @GetMapping("repeatOrder")
    public String repeatOrderPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/repeatOrder";
    }

    @PostMapping(value = "update-client")
    public String updateClient(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        System.out.println(clientDTO);


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

        return "clientProfile/editProfile";
    }

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

        return "clientProfile/changePassword";
    }


}
