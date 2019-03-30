package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.facade.OrderProductClientFacade;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.service.*;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.*;

@Controller
@RequestMapping("clientProfile")
@SessionAttributes(value = {"client", "basket"})
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
    private OrderProductClientFacade orderProductClientFacade;

    @Autowired
    private UserClientFacade userClientFacade;

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("editProfile")
    public String editProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("changePassword")
    public String changePasswordPage(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile/changePassword";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("showOrderHistory")
    public String showOrderHistoryPage(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        List<OrderDTO> orders = orderService.getOrdersByClient(clientDTO);

        model.addAttribute("orders", orders);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/orderHistory";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("repeat-order")
    public String repeatOrder(@ModelAttribute("basket") Basket basket, @RequestParam("orderId") Long orderId, Model model) {
        OrderDTO orderDTO = orderService.getOrder(orderId);

        if (orderDTO == null)
            model.addAttribute("errorMessage", "Order with id: " + orderId + " doesn't exist");
        else {
            List<OrderProductDTO> orderProductsFromDb = orderDTO.getOrderProducts();
            Map<ProductDTO, Integer> products = new HashMap<>();

            int numberOfProducts = 0, summaryPrice = 0;
            for (OrderProductDTO orderProductDTO : orderProductsFromDb) {
                products.put(orderProductDTO.getProduct(), orderProductDTO.getAmount());
                numberOfProducts++;

                summaryPrice += orderProductDTO.getProduct().getPrice();
            }

            basket = new Basket(products, numberOfProducts, summaryPrice);
        }

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getInstance());

        return "clientProfile/issueOrder";
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
    public String issueOrderPage(Model model) {
        Authentication authentication = authenticationService.getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (!authorities.isEmpty())
            for (GrantedAuthority grantedAuthority : authorities)
                if (grantedAuthority.getAuthority().equals("ROLE_CLIENT")) {
                    UserDTO userDTO = (UserDTO) authentication.getPrincipal();

                    ClientDTO clientDTO = clientService.getClientByEmail(userDTO.getEmail());

                    model.addAttribute("client", clientDTO);
                }

        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile/issueOrder";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder2")
    public String issueOrderPage2(Model model) {
        model.addAttribute("categories", categoryInfo.getInstance());
        return "clientProfile/issueOrder2";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("issue-order")
    public String createOrder(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("deliveryMethod") String deliveryMethodString, @RequestParam("paymentMethod") String paymentMethodString, @RequestParam("addressId") Long addressId, Model model) {
        ClientAddressDTO clientAddressDTO = clientAddressService.getClientAddressById(addressId);

        orderProductClientFacade.issueOrder(clientDTO, clientAddressDTO, basket, orderService.getDeliveryMethod(deliveryMethodString), orderService.getPaymentMethod(paymentMethodString));

        model.addAttribute("categories", categoryInfo.getInstance());
        model.addAttribute("basket", new Basket());
        model.addAttribute("successMessage", "Order successfully issued");
        return "clientProfile/issueOrder";
    }
}