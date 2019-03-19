package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private Client client;
    private ClientAddress clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private Set<Product> products = new HashSet<>();
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private int price;
}
