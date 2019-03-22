package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.service.UserService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.List;

@Controller
@RequestMapping("clientProfile")
@SessionAttributes(value = {"client", "authentication", "basket"})
public class ClientController {

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClientFacade userClientFacade;

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("editProfile")
    public String editProfile(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("changePassword")
    public String changePasswordPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/changePassword";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("showOrderHistory")
    public String showOrderHistoryPage(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        List<OrderDTO> orders = orderService.getOrdersByClient(clientDTO);

        if (orders.isEmpty())
            model.addAttribute("emptyListMessage", "Order list is empty.");
        else
            model.addAttribute("orders", orders);

        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/orderHistory";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("repeatOrder")
    public String repeatOrderPage(Model model) {
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/repeatOrder";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-client")
    public String updateClient(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {

            clientService.updateClient(clientDTO);

            model.addAttribute("successMessage", "Your data successfully changed");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "change-password")
    public String changePassword(@ModelAttribute("client") ClientDTO clientDTO, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("repeatNewPassword") String repeatNewPassword, Model model) {
        UserDTO userDTO = userService.getUserByEmail(clientDTO.getEmail());

        if (new BCryptPasswordEncoder().matches(password, userDTO.getPassword()))
            if (newPassword.equals(repeatNewPassword)) {
                userDTO.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                userClientFacade.updateUser(clientDTO, userDTO);

                model.addAttribute("successMessage", "Password successfully changed");
            } else {
                model.addAttribute("errorMessage", "Entered passwords do not match");
            }
        else
            model.addAttribute("errorMessage", "You entered the wrong password.");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/changePassword";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder")
    public String issueOrderPage(@ModelAttribute("basket") Basket basket, Model model) {

        model.addAttribute("basket", basket);
        model.addAttribute(categoryInfo.getInstance());
        return "clientProfile/issueOrder";
    }

    @PostMapping("issue-order")
    public String createOrder(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @ModelAttribute("basket") Basket basket, @RequestParam("deliveryMethod") DeliveryMethod deliveryMethod, @RequestParam("paymentMethod") PaymentMethod paymentMethod, Model model) {
        orderService.issueOrder(clientDTO, clientAddressDTO, basket, deliveryMethod, paymentMethod);

        model.addAttribute(categoryInfo.getInstance());
        model.addAttribute("basket", new Basket());
        return "clientProfile";
    }
}