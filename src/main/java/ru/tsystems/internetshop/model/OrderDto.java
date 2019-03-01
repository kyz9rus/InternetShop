package ru.tsystems.internetshop.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private ClientDto client;
    private ClientAddressDto clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private List<ProductDto> products;
    private PaymentStatus paymentState;
    private OrderStatus orderStatus;
}
