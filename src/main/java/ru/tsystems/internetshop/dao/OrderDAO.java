package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {
    List<Order> getUnfinishedOrdersByClient(Client client);

    List<Order> getOrdersByClient(Client client);

    List<Order> getOrdersByCategory(Category category);

    List<Order> getPaidOrders();
}