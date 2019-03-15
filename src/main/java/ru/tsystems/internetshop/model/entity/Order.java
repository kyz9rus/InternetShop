package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "ord")
@Table(name = "ord")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "SEQ_ORDER", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_address")
    private ClientAddress clientAddress;

    @Column(name = "payment_method")
    @Enumerated (EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "delivery_method")
    @Enumerated (EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_product",
            joinColumns =  @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products = new HashSet<>();

    @Column(name = "payment_status")
    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    @Enumerated (EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(Client client, ClientAddress clientAddress, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, Set<Product> products, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.products = products;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

//    @Override
//    public OrderDTO clone() throws CloneNotSupportedException {
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setId(id);
//        orderDTO.setDeliveryMethod(deliveryMethod);
//        orderDTO.setPaymentStatus(paymentStatus);
//        orderDTO.setPaymentMethod(paymentMethod);
//        orderDTO.setOrderStatus(orderStatus);
//        orderDTO.setClient(client.clone());
//        orderDTO.setClientAddress(clientAddress.clone());
//
//        Set<ProductDTO> products = new HashSet<>();
//        for (Product product : this.products)
//            products.add(product.clone());
//        orderDTO.setProducts(products);
//
//        orderDTO.setClient(client.clone());
//
//        return orderDTO;
//    }
}
