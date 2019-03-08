package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name="ord")
@Entity
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private ClientDto client;
    private ClientAddressDto clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private List<ProductDto> products; // many_to_many
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
}
