package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.model.DeliveryMethod;
import ru.tsystems.internetshop.model.OrderStatus;
import ru.tsystems.internetshop.model.PaymentMethod;
import ru.tsystems.internetshop.model.PaymentStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity(name = "ord")
@Table(name = "ord")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "SEQ_ORDER", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(cascade=CascadeType.ALL)
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
    private List<Product> products = new ArrayList<>();

    @Column(name = "payment_status")
    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "order_status")
    @Enumerated (EnumType.STRING)
    private OrderStatus orderStatus;

    private int price;

    public Order() {
    }

    public Order(Client client, ClientAddress clientAddress, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, List<Product> products, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.client = client;
        this.clientAddress = clientAddress;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.products = products;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientAddress=" + clientAddress +
                ", paymentMethod=" + paymentMethod +
                ", deliveryMethod=" + deliveryMethod +
                ", paymentStatus=" + paymentStatus +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                paymentMethod == order.paymentMethod &&
                deliveryMethod == order.deliveryMethod &&
                paymentStatus == order.paymentStatus &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, deliveryMethod, paymentStatus, orderStatus);
    }
}
