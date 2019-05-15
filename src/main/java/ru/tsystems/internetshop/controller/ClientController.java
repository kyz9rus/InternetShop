package ru.tsystems.internetshop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.tsystems.internetshop.facade.OrderProductClientFacade;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.messaging.MessageSender;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.service.*;
import ru.tsystems.internetshop.util.CategoryInfo;
import ru.tsystems.internetshop.util.ResponseInfo;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * This class is spring controller, which contains all routes, which needs authorize with role 'CLIENT'
 */
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

    @Autowired
    private MessageSender messageSender;

    private final Logger consoleLogger = Logger.getLogger("consoleLogger");
    private final Logger fileLogger = Logger.getLogger("fileLogger");

    private final String editProfilePage = "clientProfile/editProfile";
    private final String issueOrderPage = "clientProfile/issueOrder";

    private static final String categoriesAttribute = "categories";
    private static final String errorMessageAttribute = "errorMessage";
    private static final String successMessageAttribute = "successMessage";

    private static final String errorDataChangingMessage = "Data change is not possible: you have incomplete orders.";


    /**
     * This method opens editProfile page
     *
     * @return editProfile page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("editProfile")
    public String editProfile(Model model) {
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return editProfilePage;
    }

    /**
     * This method opens changePassword page
     *
     * @return changePassword page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("changePassword")
    public String changePasswordPage(Model model) {
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return "clientProfile/changePassword";
    }

    /**
     * This method opens orderHistory page
     *
     * @return orderHistory page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("orderHistory")
    public String showOrderHistoryPage(@ModelAttribute("client") ClientDTO clientDTO, Model model) {
        consoleLogger.info("Showing order history for client " + clientDTO + "...");
        fileLogger.info("Showing order history for client " + clientDTO + "...");

        model.addAttribute("orders", orderService.getOrdersByClient(clientDTO));
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return "clientProfile/orderHistory";
    }

    /**
     * This method allows to repeat one of the previous orders and return
     *
     * @return issueOrder page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("repeat-order")
    public String repeatOrder(@ModelAttribute("basket") Basket basket, @RequestParam("orderId") Long orderId, Model model) {
        consoleLogger.info("Combine basket for repeating order for order with id " + orderId + "...");
        fileLogger.info("Combine basket for repeating order for order with id " + orderId + "...");

        OrderDTO orderDTO = orderService.getOrder(orderId);

        if (orderDTO == null)
            model.addAttribute("errorMessage", "Order with id: " + orderId + " doesn't exist");
        else
            basket = orderService.repeatOrder(basket, orderDTO);

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("basket", basket);
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return issueOrderPage;
    }

    /**
     * This method allows to update client data
     *
     * @return editProfile page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-client")
    public String updateClient(@RequestParam("clientEmail") String email, @ModelAttribute("client") ClientDTO clientDTO, Model model) {
        consoleLogger.info("Updating client " + clientDTO + " and old email: " + email + "...");
        fileLogger.info("Updating client " + clientDTO + " and old email: " + email + "...");

        model = clientService.updateClient(email, clientDTO, model);

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return editProfilePage;
    }

    /**
     * This method allows to update client password
     *
     * @return changePassword page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "password")
    public String changePassword(@ModelAttribute("client") ClientDTO clientDTO, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword, @RequestParam("repeatNewPassword") String repeatNewPassword, Model model) {
        consoleLogger.info("Changing password for client " + clientDTO + "...");
        fileLogger.info("Changing password for client " + clientDTO + "...");

        model = userService.changePassword(clientDTO, password, newPassword, repeatNewPassword, model);

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return "clientProfile/changePassword";
    }

    /**
     * This method allows to create new address for the client
     *
     * @return editProfile page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "create-address")
    public String createClientAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, Model model) {
        consoleLogger.info("Creating address for client " + clientDTO + "...");
        fileLogger.info("Creating address for client " + clientDTO + "...");

        model = clientAddressService.createAddress(clientDTO, clientAddressDTO, model);

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return editProfilePage;
    }

    /**
     * This method allows to update the address for the client
     *
     * @return editProfile page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "update-address")
    public String updateClientAdress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        consoleLogger.info("Updating address for client " + clientDTO + "...");
        fileLogger.info("Updating address for client " + clientDTO + "...");

        clientAddressDTO.setId(addressId);
        clientAddressDTO.setClient(clientDTO);

        model = clientAddressService.updateClientAddress(clientAddressDTO, model);

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return editProfilePage;
    }

    /**
     * This method allows to delete the address for the client
     *
     * @return editProfile page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "delete-address")
    public String deleteAddress(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("clientAddress") ClientAddressDTO clientAddressDTO, @RequestParam("addressId") Long addressId, Model model) {
        consoleLogger.info("Deleting address for client " + clientDTO + "...");
        fileLogger.info("Deleting address for client " + clientDTO + "...");

        clientAddressDTO.setId(addressId);
        clientAddressDTO.setClient(clientDTO);

        model = clientAddressService.deleteClientAddress(clientAddressDTO, model);

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return editProfilePage;
    }

    /**
     * This method gets all products for the order
     *
     * @return ResponseEntity with products and status
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping(value = "/showOrderHistory/get-products", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<List<OrderProductDTO>> getProducts(@RequestParam("orderId") Long orderId) {
        consoleLogger.info("Getting products for order with id " + orderId + "...");
        fileLogger.info("Getting products for order with id " + orderId + "...");

        List<OrderProductDTO> orderProducts = orderService.getOrder(orderId).getOrderProducts();

        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }

    /**
     * This method resets discount if it has and to open first page for issuing order
     *
     * @return issueOrder page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder")
    public String issueOrderPage(@ModelAttribute("basket") Basket basket, Model model) {
        consoleLogger.info("Reseting discount (if was)" + "...");
        fileLogger.info("Reseting discount (if was)" + "...");

        basketService.resetDiscount(basket);
        model.addAttribute("client", authenticationService.getClient());
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return issueOrderPage;
    }

    /**
     * This method checks coupon for the client
     *
     * @return ResponseInfo with message and status
     */
    @PostMapping(value = "/issueOrder/check-coupon", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseInfo checkCoupon(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("couponValue") String couponValue, Model model) {
        consoleLogger.info("Checking coupon " + couponValue + " for client " + clientDTO + "...");
        fileLogger.info("Checking coupon " + couponValue + " for client " + clientDTO + "...");

        ResponseInfo responseInfo = couponService.checkCoupon(couponValue, clientDTO, model);

        model.addAttribute("basket", basket);
        return responseInfo;
    }

    /**
     * This method allows to calc price with or without coupon and open next page for issuing order
     *
     * @return second issueOrder page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @GetMapping("issueOrder2")
    public String issueOrderPage2(@RequestParam("couponValue") String couponValue, @ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, Model model) {
        consoleLogger.info("Calculating price for order with couponValue " + couponValue + " and basket " + basket + "...");
        fileLogger.info("Calculating price for order with couponValue " + couponValue + " and basket " + basket + "...");

        if (clientDTO.getAddresses().isEmpty())
            model.addAttribute(errorMessageAttribute, "Please add address in your profile");
        else
            basketService.applyCoupon(couponValue, basket);

        model.addAttribute("basket", basket);
        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        return "clientProfile/issueOrder2";
    }

    /**
     * This method allows to issue order
     *
     * @return issueOrder page
     */
    @PreAuthorize("hasAnyRole('CLIENT')")
    @PostMapping("issue-order")
    public String createOrder(@ModelAttribute("client") ClientDTO clientDTO, @ModelAttribute("basket") Basket basket, @RequestParam("deliveryMethod") String deliveryMethodString, @RequestParam("paymentMethod") String paymentMethodString, @RequestParam("addressId") Long addressId, Model model) throws Exception {
        consoleLogger.info("Collecting order for client" + clientDTO + " with basket " + basket + " with delivery method " + deliveryMethodString + " with payment mathod " + paymentMethodString + " with address id " + addressId + "...");
        fileLogger.info("Collecting order for client" + clientDTO + " with basket " + basket + " with delivery method " + deliveryMethodString + " with payment mathod " + paymentMethodString + " with address id " + addressId + "...");

        ClientAddressDTO clientAddressDTO = clientAddressService.getClientAddressById(addressId);

        orderProductClientFacade.issueOrder(clientDTO, clientAddressDTO, basket, orderService.getDeliveryMethod(deliveryMethodString), orderService.getPaymentMethod(paymentMethodString));

        model.addAttribute(categoriesAttribute, categoryInfo.getCategories());
        model.addAttribute("basket", new Basket());
        model.addAttribute(successMessageAttribute, "Order successfully issued");
        messageSender.sendMessage("Top products could changed");
        return issueOrderPage;
    }

    @RequestMapping(value = "/goodbye", method = POST)
    public String goodbye(SessionStatus status) {
        status.setComplete();
        return "index";
    }
}