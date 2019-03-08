package ru.tsystems.internetshop.controller;

import ru.tsystems.internetshop.model.ClientDto;
import ru.tsystems.internetshop.model.ClientListContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MappingController {

    @GetMapping(value = "/")
    public String main(Model model) {
        List<ClientDto> clients = new ArrayList<>();
        clients.add(new ClientDto("Daniil", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));
        clients.add(new ClientDto("Daniil2", "Kuzchutkomov", LocalDate.of(1998, 4, 23), "kyz9rus@yandex.ru", "null"));

        ClientListContainer clientListContainer = new ClientListContainer();
        clientListContainer.setClients(clients);

        model.addAttribute("Clients", clientListContainer);
        return "index";
    }

    @GetMapping(value = "registration")
    public String toLoginPage(Model model) {
        model.addAttribute("client", new ClientDto());
        return "registration";
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
