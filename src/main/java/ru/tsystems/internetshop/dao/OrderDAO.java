package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {
    void createOrUpdate(Order order);

    List<Order> getUnfinishedOrdersByClient(Client client);

    List<Order> getOrdersByClient(Client client);
}