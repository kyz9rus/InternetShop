package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order, Long> {
    List<Order> findOrdersByClientAndWithDeliveredStatus(ClientDTO clientDTO);
}