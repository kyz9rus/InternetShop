package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderService {
    void updateOrder(Order order);

    Order getOrder(Long id);

    List<Order> getOrders();
}
