package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Order;

import java.util.List;

public interface OrderDAO {
    Order findById(Long id);

    void saveOrder(Order order);

    void updateOrder(Order order);

    List findAllOrders();
}