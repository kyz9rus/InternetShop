package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ord")
@Table(name = "ord")
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_id")
//    private ClientDto client;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_address")
//    private ClientAddressDto clientAddress;

    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;


//    private DeliveryMethod deliveryMethod;

//    private List<ProductDto> products; // many_to_many

    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    public OrderDto() {
    }

    public OrderDto(int id, PaymentMethod paymentMethod, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }
}
