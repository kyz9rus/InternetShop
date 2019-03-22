package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.entity.Order;

import java.util.List;

public interface OrderService {
    void issueOrder(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Basket basket, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod);

    void updateOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersByClient(ClientDTO clientDTO);

    List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO);
}
