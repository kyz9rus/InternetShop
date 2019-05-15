package ru.tsystems.internetshop.controller;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.log4j.Category;
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

    /**
     * This method gets all orders
     *
     * @return all orders
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("get-orders")
    public String getOrders(Model model) {
        consoleLogger.info("Get all orders...");
        fileLogger.info("Get all orders...");

        model.addAttribute("orders", orderService.getOrders());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/orders";
    }

    /**
     * This method gets client statistic (with all clients)
     *
     * @return topClients page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("clientStatistic")
    public String clientStatisticPage(Model model) {
        model.addAttribute("clients", clientService.getTop10Clients());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/topClients";
    }

    /**
     * This method gets product statistic (with 10 products)
     *
     * @return topProducts page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("productStatistic")
    public String productStatisticPage(Model model) {
        model.addAttribute("products", productService.getTop10Products());
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/topProducts";
    }

    /**
     * This method gets revenue for a week and month
     *
     * @return revenue page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("revenue")
    public String revenuePage(Model model) {
        RevenueInfo revenueInfo = orderService.getRevenueInfo();

        model.addAttribute("revenue", revenueInfo);
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/statistic/revenue";
    }

    /**
     * This method opens addProduct page
     *
     * @return addProduct page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("addProduct")
    public String addProductPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/addProduct";
    }

    /**
     * This method opens manageCategories page
     *
     * @return manageCategories page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping("manageCategories")
    public String manageCategoriesPage(Model model) {
        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    /**
     * This method changes order status
     *
     * @param id                order id
     * @param orderStatusString new order status
     * @return success or error message
     */
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
                fileLogger.error(e);
                throw e;
            }

            return new ResponseInfo("Payment status for order with id " + orderDTO.getId() + " successfully changed!", 200);
        } else
            return new ResponseInfo("Order with id " + id + " doesn't exist. Inform the administrator!", 404);

    }

    /**
     * This method changes payment status
     *
     * @param id                  order id
     * @param paymentStatusString new payment status
     * @return success or error message
     */
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
                fileLogger.error(e);
                throw e;
            }

            return new ResponseInfo("Payment status for order with id " + orderDTO.getId() + " successfully changed!", 200);
        } else
            return new ResponseInfo("Order with id " + id + " doesn't exist. Inform the administrator!", 404);
    }

    /**
     * This method creates new product
     *
     * @return addProduct page
     */
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

                CategoryDTO categoryDTO = categoryService.getCategoryByName(productDTO.getCategory().getName().toLowerCase());
                productDTO.setCategory(categoryDTO);

                productService.saveProduct(productDTO);

                model.addAttribute("successMessage", "Product saved successfully.");
                messageSender.sendMessage("Top products could changed");
            } else
                model.addAttribute("errorMessage", "Product with this name already exists.");
        }

        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/addProduct";
    }

    /**
     * This method creates new category
     *
     * @return manageCategories page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("create-category")
    public String createCategory(@ModelAttribute("category") CategoryDTO categoryDTO, Model model) {
        consoleLogger.info("Creating category " + categoryDTO + "...");
        fileLogger.info("Creating category " + categoryDTO + "...");

        categoryDTO.setName(categoryDTO.getName().toLowerCase());

        if (categoryService.getCategoryByName(categoryDTO.getName()) == null) {

            categoryDTO = categoryService.saveCategory(categoryDTO);

            categoryInfo.getCategories().clear();

            List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

            for (CategoryDTO dto : categoryDTOS)
                fileLogger.info(dto);

            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getCategories().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else
            model.addAttribute("errorMessage", "Category already exist.");


        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    /**
     * This method updates the category
     *
     * @return manageCategories page
     */
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

    /**
     * This method remove the category
     *
     * @return manageCategories page
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping("remove-category")
    public String removeCategory(@RequestParam("oldName") String categoryName, Model model) {
        consoleLogger.info("Removing category '" + categoryName + "'...");
        fileLogger.info("Removing category '" + categoryName + "'...");

        model = categoryService.removeCategory(categoryName, model);

        model.addAttribute("categories", categoryInfo.getCategories());
        return "employeeProfile/manageCategories";
    }

    /**
     * This method gets all products for order
     *
     * @param orderId order id
     * @return list of products
     */
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "/showOrderHistory/get-products", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    List<OrderProductDTO> getProducts(@RequestParam("orderId") Long orderId) {
        consoleLogger.info("Getting products for order with id " + orderId + "...");
        fileLogger.info("Getting products for order with id " + orderId + "...");

        return orderService.getOrder(orderId).getOrderProducts();
    }


}
