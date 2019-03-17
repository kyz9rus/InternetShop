package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderService {
    void updateOrder(Order order);

    OrderDTO getOrder(Long id);

    List<Order> getOrders();

    List<OrderDTO> getOrdersByClientAndDeliveredStatus(ClientDTO clientDTO);
}
