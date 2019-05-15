package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.Basket;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

/**
 * This is interface, which declares methods for basket
 */
public interface BasketService {
    int calcPrice(Basket basket);
    int calcPriceWithoutDiscount(Basket basket);

    void resetDiscount(Basket basket);

    ProductDTO putProduct(Basket basket, Long productId);

    ProductDTO decreaseProduct(Basket basket, Long productId);

    ProductDTO removeProduct(Basket basket, Long productId);

    void applyCoupon(String couponValue, Basket basket);
}
