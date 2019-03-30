package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "order_product")
@Table(name = "order_product")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;

    public OrderProduct() {
    }
}
