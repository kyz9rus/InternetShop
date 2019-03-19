package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderService {
    void updateOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersByClient(ClientDTO clientDTO);

    List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO);
}
