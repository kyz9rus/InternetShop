package ru.tsystems.internetshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.service.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("employeeProfile")
public class EmployeeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @PostMapping("create-product")
    public ModelAndView createProduct(@Validated @ModelAttribute("product") ProductDto product) {
        System.out.println(product);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");

        if (productService.getProductByName(product.getName()) == null) {
            productService.saveProduct(product);

            modelAndView.addObject("successMessage", "Product saved successfully!");
        } else
            modelAndView.addObject("errorMessage", "Product with this name already exists.");

        return modelAndView;
    }

    @GetMapping("getOrders")
    public ModelAndView getOrders() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");

//        List<OrderDto> orders = orderService.getOrders();
        List<OrderDto> orders = new ArrayList<>();

//        public OrderDto(PaymentMethod paymentMethod, PaymentStatus paymentStatus, OrderStatus orderStatus) {

        orders.addAll(Arrays.asList(new OrderDto(1, PaymentMethod.cash, PaymentStatus.paid, OrderStatus.delivered), new OrderDto(2, PaymentMethod.cash, PaymentStatus.paid, OrderStatus.delivered), new OrderDto(3, PaymentMethod.cash, PaymentStatus.paid, OrderStatus.delivered)));

        if (orders.isEmpty())
            modelAndView.addObject("emptyListMessage", "Order list is empty.");
        else
            modelAndView.addObject("orders", orders);

        return modelAndView;
    }

    @PostMapping("change-order-status")
    public ModelAndView changeOrderStatus(@RequestParam("id") int id, @RequestParam("orderStatus") OrderStatus orderStatus) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");

        OrderDto order = orderService.getOrder(id);
        if (order != null) {
            order.setOrderStatus(orderStatus);
            orderService.updateOrder(order);

            modelAndView.addObject("successMessage", "Order status for order with id " + order.getId() + " successfully changed!");
        } else
            modelAndView.addObject("errorMessage", "Order with id " + id + " doesn't exist. Inform the administrator!");

        return modelAndView;
    }
}
