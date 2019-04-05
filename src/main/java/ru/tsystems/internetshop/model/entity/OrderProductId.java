//package ru.tsystems.internetshop.model.entity;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Embeddable;
//import javax.persistence.ManyToOne;
//import java.io.Serializable;
//
//@Embeddable
//public class OrderProductId implements Serializable {
//    private Order order;
//    private Product product;
//
//    @ManyToOne(cascade = {CascadeType.ALL})
//    public Order getOrder() {
//        return order;
//    }
//
//    @ManyToOne(cascade = {CascadeType.ALL})
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//}
