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
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.service.ClientAddressService;
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
    private ClientAddressService clientAddressService;

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

        model.addAttribute("orders", orders);
        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/orderHistory";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("repeat-order")
    public String repeatOrder(@RequestParam("orderId") Long orderId, Model model) {
        OrderDTO orderDTO = orderService.getOrder(orderId);

        if (orderDTO == null)
            model.addAttribute("errorMessage", "Order with id: " + orderId + " doesn't exist");
        else {
            orderDTO.setId(null);
            orderDTO.setPaymentStatus(PaymentStatus.WAITING_FOR_PAYMENT);
            orderDTO.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);
            orderService.repeatOrder(orderDTO);

            model.addAttribute("successMessage", "Order successfully submitted");
        }

        model.addAttribute("orders", orderService.getOrders());

        model.addAttribute(categoryInfo.getInstance());

        return "clientProfile/orderHistory";
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
    @PostMapping(value = "create-address")
    public String createClientAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, Model model) {
        clientAddressDTO.setClient(clientDTO);
        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {
            clientAddressService.saveClientAddress(clientAddressDTO);

            clientDTO.setAddresses(clientAddressService.getAddressesByClient(clientDTO));

            model.addAttribute("successMessage", "Your data successfully saved");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-address")
    public String updateClientAdress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        clientAddressDTO.setId(addressId);
        clientAddressDTO.setClient(clientDTO);
        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {
            clientAddressService.updateClientAddress(clientAddressDTO);

            clientDTO.setAddresses(clientAddressService.getAddressesByClient(clientDTO));

            model.addAttribute("successMessage", "Your data successfully changed");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "delete-address")
    public String deleteAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        clientAddressDTO.setId(addressId);
        clientAddressDTO.setClient(clientDTO);
        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {
            clientAddressService.deleteAddress(clientAddressDTO);

            clientDTO.setAddresses(clientAddressService.getAddressesByClient(clientDTO));

            model.addAttribute("successMessage", "Your address successfully deleted");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder")
    public String issueOrderPage(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, Model model) {
        model.addAttribute("client", clientDTO);
        model.addAttribute("basket", basket);
        model.addAttribute(categoryInfo.getInstance());
        return "clientProfile/issueOrder";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("issue-order")
    public String createOrder(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("deliveryMethod") String deliveryMethodString, @RequestParam("paymentMethod") String paymentMethodString, @RequestParam("addressId") Long addressId, Model model) {
        ClientAddressDTO clientAddressDTO = clientAddressService.getClientAddressById(addressId);

        orderService.issueOrder(clientDTO, clientAddressDTO, basket, orderService.getDeliveryMethod(deliveryMethodString), orderService.getPaymentMethod(paymentMethodString));

        model.addAttribute(categoryInfo.getInstance());
        model.addAttribute("basket", new Basket());
        model.addAttribute("successMessage", "Order successfully issued");
        return "clientProfile/issueOrder";
    }
}