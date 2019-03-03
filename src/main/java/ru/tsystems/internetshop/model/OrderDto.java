package ru.tsystems.internetshop.model;

import lombok.Data;

import java.util.List;

@Data
//"Table(name="ord")
public class OrderDto {
    private ClientDto client;
    private ClientAddressDto clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private List<ProductDto> products; // many_to_many
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
}
