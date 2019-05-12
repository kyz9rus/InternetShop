package ru.tsystems.internetshop.controller;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.messaging.MessageSender;
import ru.tsystems.internetshop.model.DTO.*;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.RevenueInfo;
import ru.tsystems.internetshop.service.*;
import ru.tsystems.internetshop.util.CategoryInfo;
import ru.tsystems.internetshop.util.ResponseInfo;

import java.util.List;

/**
 * This class is spring controller, which contains all routes, which needs authorize with role 'EMPLOYEE'
 */
@Controller
@RequestMapping("employeeProfile")
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private ClientService clientService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private MailService mailService;

    private final Logger consoleLogger = Logger.getLogger("consoleLogger");
    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("get-orders")
    public String getOrders(Model model) {
        consoleLogger.info("Get all orders...");
        fileLogger.info("Get all orders...");

        List<OrderDTO> orders = orderService.getOrders();

        model.addAttribute("orders", orders);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/orders";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("clientStatistic")
    public String clientStatisticPage(Model model) {
        model.addAttribute("clients", clientService.getTop10Clients());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/topClients";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("productStatistic")
    public String productStatisticPage(Model model) {
        model.addAttribute("products", productService.getTop10Products());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/topProducts";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("revenue")
    public String revenuePage(Model model) {
        RevenueInfo revenueInfo = orderService.getRevenueInfo();

        model.addAttribute("revenue", revenueInfo);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/revenue";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/addProduct";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("manageCategories")
    public String manageCategoriesPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("change-order-status")
    public @ResponseBody
    ResponseInfo changeOrderStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") String orderStatusString) throws SMTPSendFailedException {
        consoleLogger.info("Changing order status (-> " + orderStatusString + ") for order with id + " + id + "...");
        fileLogger.info("Changing order status (-> " + orderStatusString + ") for order with id + " + id + "...");

        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO != null) {
            orderDTO.setOrderStatus(orderService.getOrderStatus(orderStatusString));

            for (OrderProductDTO orderProductDTO : orderDTO.getOrderProducts()) {
                ProductDTO productDTO = orderProductDTO.getProduct();
                productService.updateProduct(productDTO);
            }

            orderService.updateOrder(orderDTO);

            clientService.updateClient(orderDTO.getClient());

            try {
                mailService.sendChangeOrderStatusLetter(orderDTO);
            } catch (MailException e) {
                e.printStackTrace();
                throw e;
            }

            return new ResponseInfo("Payment status for order with id " + orderDTO.getId() + " successfully changed!", 200);
        } else
            return new ResponseInfo("Order with id " + id + " doesn't exist. Inform the administrator!", 404);

    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("change-payment-status")
    public @ResponseBody
    ResponseInfo changePaymentStatus(@RequestParam("id") Long id, @RequestParam("paymentStatus") String paymentStatusString) throws SMTPSendFailedException {
        consoleLogger.info("Changing payment status for order with id + " + id + "...");
        fileLogger.info("Changing payment status for order with id + " + id + "...");

        OrderDTO orderDTO = orderService.getOrder(id);
        if (orderDTO != null) {
            orderDTO.setPaymentStatus(orderService.getPaymentStatus(paymentStatusString));

            for (OrderProductDTO orderProductDTO : orderDTO.getOrderProducts()) {
                ProductDTO productDTO = orderProductDTO.getProduct();
                productService.updateProduct(productDTO);
            }

            orderService.updateOrder(orderDTO);

            ClientDTO clientDTO = orderDTO.getClient();
            if (orderDTO.getPaymentStatus() == PaymentStatus.PAID)
                clientDTO.setSummaryOrdersPrice(clientDTO.getSummaryOrdersPrice() + (long) orderDTO.getPrice());
            else
                clientDTO.setSummaryOrdersPrice(clientDTO.getSummaryOrdersPrice() - (long) orderDTO.getPrice());

            clientService.updateClient(orderDTO.getClient());

            try {
                mailService.sendChangePaymentStatusLetter(orderDTO);
            } catch (MailException e) {
                e.printStackTrace();
                throw e;
            }

            return new ResponseInfo("Payment status for order with id " + orderDTO.getId() + " successfully changed!", 200);
        } else
            return new ResponseInfo("Order with id " + id + " doesn't exist. Inform the administrator!", 404);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("create-product")
    public String createProduct(@Validated @ModelAttribute("product") ProductDTO productDTO, Model model) {
        consoleLogger.info("Creating product " + productDTO + "...");
        fileLogger.info("Creating product " + productDTO + "...");

        if (productDTO.getQuantityInStock() <= 0) {
            model.addAttribute("errorMessage", "Quantity in stock cannot be negative");
        } else {
            if (productService.getProductByName(productDTO.getName()) == null) {

                if (productDTO.getImgSrc().length() == 0)
                    productDTO.setDefaultImgSrc();

                productService.saveProduct(productDTO);

                model.addAttribute("successMessage", "Product saved successfully.");
                messageSender.sendMessage("Top products could changed");
            } else
                model.addAttribute("errorMessage", "Product with this name already exists.");
        }

        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/addProduct";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("create-category")
    public String createCategory(@ModelAttribute("category") CategoryDTO categoryDTO, Model model) {
        consoleLogger.info("Creating category " + categoryDTO + "...");
        fileLogger.info("Creating category " + categoryDTO + "...");

        categoryDTO.setName(categoryDTO.getName().toLowerCase());

        if (categoryService.getCategoryByName(categoryDTO.getName()) == null) {

            categoryService.saveCategory(categoryDTO);

            categoryInfo.getCategories().clear();

            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getCategories().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else
            model.addAttribute("errorMessage", "Category already exist.");

        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("update-category")
    public String updateCategory(@ModelAttribute("category") CategoryDTO categoryDTO, @RequestParam("oldName") String oldName, Model model) {
        consoleLogger.info("Updating category " + categoryDTO + "...");
        fileLogger.info("Updating category " + categoryDTO + "...");

        categoryDTO.setName(categoryDTO.getName().toLowerCase());
        oldName = oldName.toLowerCase();

        categoryService.updateCategory(oldName, categoryDTO);

        categoryInfo.getCategories().clear();

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

        categoryInfo.getCategories().addAll(categoryDTOS);

        model.addAttribute("successMessage", "Category successfully changed.");
        model.addAttribute("categories", categoryInfo.getCategories());

        return "employeeProfile/manageCategories";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("remove-category")
    public String removeCategory(@RequestParam("oldName") String categoryName, Model model) {
        consoleLogger.info("Removing category '" + categoryName + "'...");
        fileLogger.info("Removing category '" + categoryName + "'...");

        CategoryDTO categoryDTO = categoryService.getCategoryByName(categoryName);
        List<OrderDTO> orders = orderService.getOrdersByCategory(categoryDTO);
        if (orders.isEmpty()) {
            categoryName = categoryName.toLowerCase();

            categoryService.removeCategoryByName(categoryName);

            categoryInfo.getCategories().clear();

            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getCategories().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else {
            model.addAttribute("errorMessage", "Category cannot be removed (There are incomplete orders)");
        }

        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "/showOrderHistory/get-products", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<OrderProductDTO> getProducts(@RequestParam("orderId") Long orderId) {
        consoleLogger.info("Getting products for order with id " + orderId + "...");
        fileLogger.info("Getting products for order with id " + orderId + "...");

        OrderDTO orderDTO = orderService.getOrder(orderId);

        return orderDTO.getOrderProducts();
    }
}
