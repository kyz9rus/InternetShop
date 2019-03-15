package ru.tsystems.internetshop.facade;

import ru.tsystems.internetshop.model.DTO.OrderDTO;

import java.util.List;

public interface OrderFacade {
    List<OrderDTO> getOrders();
}