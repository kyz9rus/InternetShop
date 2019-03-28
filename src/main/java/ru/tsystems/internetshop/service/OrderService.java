package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.*;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;

import java.util.List;

public interface OrderService {
    void updateOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersByClient(ClientDTO clientDTO);

    List<OrderDTO> getUnfinishedOrdersByClient(ClientDTO clientDTO);

    DeliveryMethod getDeliveryMethod(String deliveryMethodString);

    PaymentMethod getPaymentMethod(String paymentMethodString);

    PaymentStatus getPaymentStatus(String paymentStatusString);

    OrderStatus getOrderStatus(String orderStatusString);

    List<OrderDTO> getOrdersByCategory(CategoryDTO categoryDTO);

    RevenueInfo getRevenueInfo();
}
