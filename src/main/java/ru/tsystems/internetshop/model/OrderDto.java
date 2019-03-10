package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "ord")
@Table(name = "ord")
public class OrderDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientDto client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_address")
    private ClientAddressDto clientAddress;

    @Column(name = "payment_method")
    @Enumerated (EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "delivery_method")
    @Enumerated (EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns =  @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<ProductDto> products = new HashSet<>();

    @Column(name = "payment_status")
    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    @Enumerated (EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderDto() {
    }

    public OrderDto(ClientDto client, ClientAddressDto clientAddress, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, Set<ProductDto> products, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.products = products;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }
}
