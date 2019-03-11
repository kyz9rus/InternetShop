package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Order;

import java.util.List;

public interface OrderService {
    void saveOrder(Order order);

    void updateOrder(Order order);

    Order getOrder(Long id);

    List<Order> getOrders();
}
