package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.OrderDto;

import java.util.List;

public interface OrderDAO {
    OrderDto findById(Long id);

    void saveOrder(OrderDto order);

    void updateOrder(OrderDto order);

    List<OrderDto> findAllOrders();
}