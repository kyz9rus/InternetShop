package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

/**
 * This interface extends DAO interface and declares special methods for Order class
 */
public interface OrderDAO extends DAO<Order, Long> {
    List<Order> findUnfinishedOrdersByClient(Client client);

    List<Order> findOrdersByClient(Client client);

    List<Order> findOrdersByCategory(Category category);

    List<Order> findPaidOrders();

    Order createAndGet(Order entity);
}