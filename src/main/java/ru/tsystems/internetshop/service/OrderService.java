package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderService {
    void updateOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersByClient(ClientDTO clientDTO);

    List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO);

    DeliveryMethod getDeliveryMethod(String deliveryMethodString);

    PaymentMethod getPaymentMethod(String paymentMethodString);
}
