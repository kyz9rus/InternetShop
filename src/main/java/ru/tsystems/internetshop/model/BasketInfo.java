package ru.tsystems.internetshop.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BasketInfo {
    private Basket basket;
    private Integer numberOfProduct;

    public BasketInfo() {
        basket = new Basket();
    }

    public BasketInfo(Basket basket, Integer numberOfProduct) {
        this.basket = basket;
        this.numberOfProduct = numberOfProduct;
    }
}