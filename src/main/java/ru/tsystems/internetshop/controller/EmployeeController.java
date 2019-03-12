package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.CategoryInfo;

import java.util.List;

@Controller
@RequestMapping("employeeProfile")
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CategoryInfo categoryInfo;

    @PostMapping("create-product")
    public String createProduct(@Validated @ModelAttribute("product") Product product, Model model) {
        System.out.println(product);

        if (productService.getProductByName(product.getName()) == null) {
            productService.saveProduct(product);

            model.addAttribute("successMessage", "Product saved successfully!");
        } else
            model.addAttribute("errorMessage", "Product with this name already exists.");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile";
    }

    @GetMapping("getOrders")
    public String getOrders(Model model) {
        List<Order> orders = orderService.getOrders();

        if (orders.isEmpty())
            model.addAttribute("emptyListMessage", "Order list is empty.");
        else
            model.addAttribute("orders", orders);

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile";
    }

    @PostMapping("change-order-status")
    public String changeOrderStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") OrderStatus orderStatus, Model model) {

        Order order = orderService.getOrder(id);
        if (order != null) {
            order.setOrderStatus(orderStatus);
            orderService.updateOrder(order);

            model.addAttribute("successMessage", "Order status for order with id " + order.getId() + " successfully changed!");
        } else
            model.addAttribute("errorMessage", "Order with id " + id + " doesn't exist. Inform the administrator!");

        model.addAttribute("categories", categoryInfo.getInstance());

        return "employeeProfile";
    }
}
