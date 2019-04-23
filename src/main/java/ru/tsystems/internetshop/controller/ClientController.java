package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ru.tsystems.internetshop.util.ResponseInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

    @Autowired
    private CouponService couponService;

    @Autowired
    private BasketService basketService;

    private Logger logger = Logger.getLogger("logger");

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("editProfile")
    public String editProfile(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("changePassword")
    public String changePasswordPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/changePassword";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("orderHistory")
    public String showOrderHistoryPage(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        logger.info("Showing order history for client " + clientDTO + "...");

        List<OrderDTO> orders = orderService.getOrdersByClient(clientDTO);

        model.addAttribute("orders", orders);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/orderHistory";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("repeat-order")
    public String repeatOrder(@ModelAttribute("basket") Basket basket, @RequestParam("orderId") Long orderId, Model model) {
        logger.info("Combine basket for repeating order for order with id " + orderId + "...");

        OrderDTO orderDTO = orderService.getOrder(orderId);

        if (orderDTO == null)
            model.addAttribute("errorMessage", "Order with id: " + orderId + " doesn't exist");
        else {
            List<OrderProductDTO> orderProductsFromDb = orderDTO.getOrderProducts();
            Map<ProductDTO, Integer> products = new HashMap<>();

            int numberOfProducts = 0, summaryPrice = 0;
            for (OrderProductDTO orderProductDTO : orderProductsFromDb) {
                products.put(orderProductDTO.getProduct(), orderProductDTO.getAmount());
                numberOfProducts += orderProductDTO.getAmount();

                summaryPrice += orderProductDTO.getProduct().getPrice() * orderProductDTO.getAmount();
            }

            basket = new Basket(products, numberOfProducts, summaryPrice);
        }

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/issueOrder";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-client")
    public String updateClient(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        logger.info("Updating client " + clientDTO + "...");

        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {

            clientService.updateClient(clientDTO);

            model.addAttribute("successMessage", "Your data successfully changed");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "change-password")
    public String changePassword(@ModelAttribute("client") ClientDTO clientDTO, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("repeatNewPassword") String repeatNewPassword, Model model) {
        logger.info("Changing password for client " + clientDTO + "...");

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

        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/changePassword";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "create-address")
    public String createClientAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, Model model) {
        logger.info("Creating address for client " + clientDTO + "...");

        clientAddressDTO.setClient(clientDTO);
        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {
            clientAddressService.saveClientAddress(clientAddressDTO);

            clientDTO.setAddresses(clientAddressService.getAddressesByClient(clientDTO));

            model.addAttribute("successMessage", "Your data successfully saved");
            model.addAttribute("client", clientDTO);
        }

        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-address")
    public String updateClientAdress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        logger.info("Updating address for client " + clientDTO + "...");

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

        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "delete-address")
    public String deleteAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        logger.info("Deleting address for client " + clientDTO + "...");

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

        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/editProfile";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "/showOrderHistory/get-products", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<List<OrderProductDTO>> getProducts(@RequestParam("orderId") Long orderId){
        logger.info("Getting products for order with id " + orderId + "...");

        OrderDTO orderDTO = orderService.getOrder(orderId);

        List<OrderProductDTO> orderProducts = orderDTO.getOrderProducts();

        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder")
    public String issueOrderPage(Model model) {
        model.addAttribute("client", authenticationService.getClient());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/issueOrder";
    }

    @PostMapping(value = "/issueOrder/check-coupon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseInfo checkCoupon(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("couponValue") String couponValue, Model model) {
        logger.info("Checking coupon " + couponValue + " for client " + clientDTO + "...");

        ResponseInfo responseInfo;

        CouponDTO couponDTO = couponService.getCouponByValue(couponValue);
        if (couponDTO == null)
            responseInfo = new ResponseInfo("Incorrect coupon", 404);
        else
            if (clientDTO.getCoupons().contains(couponDTO))
                responseInfo = new ResponseInfo("You have already use this coupon", 404);
            else
                responseInfo = new ResponseInfo("Correct coupon", 200);

        model.addAttribute("basket", basket);
        return responseInfo;
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder2")
    public String issueOrderPage2(@RequestParam("couponValue") String couponValue, @ModelAttribute("basket") Basket basket, Model model) {
        logger.info("Calculating price for order with couponValue " + couponValue + " and basket " + basket + "...");

        CouponDTO couponDTO = couponService.getCouponByValue(couponValue);

        if (couponDTO != null && (basket.getCouponDTO() == null || basket.isChangedAfterCoupon())) {
            if (couponDTO.getName().equals("FIRST_ORDER"))
                basket.setSummaryPrice((int) Math.round(basketService.calcPrice(basket) * 0.7));

            basket.setChangedAfterCoupon(false);
        }

        basket.setCouponDTO(couponDTO);

        model.addAttribute("basket", basket);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "clientProfile/issueOrder2";
    }

    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("issue-order")
    public String createOrder(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("deliveryMethod") String deliveryMethodString, @RequestParam("paymentMethod") String paymentMethodString, @RequestParam("addressId") Long addressId, Model model) throws Exception {
        logger.info("Collecting order for client" + clientDTO + " with basket " + basket + " with delivery method " + deliveryMethodString + " with payment mathod " + paymentMethodString + " with address id " + addressId + "...");

        ClientAddressDTO clientAddressDTO = clientAddressService.getClientAddressById(addressId);

        orderProductClientFacade.issueOrder(clientDTO, clientAddressDTO, basket, orderService.getDeliveryMethod(deliveryMethodString), orderService.getPaymentMethod(paymentMethodString));

        model.addAttribute("categories", categoryInfo.getCategories());
        model.addAttribute("basket", new Basket());
        model.addAttribute("successMessage", "Order successfully issued");
        return "clientProfile/issueOrder";
    }
}