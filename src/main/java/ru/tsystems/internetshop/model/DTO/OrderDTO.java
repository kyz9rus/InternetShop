package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private ClientDTO client;
    private ClientAddressDTO clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private Set<ProductDTO> products = new HashSet<>();
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
}
