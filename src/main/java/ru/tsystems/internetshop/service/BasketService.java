package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Basket;

/**
 * This is interface, which declares methods for basket
 */
public interface BasketService {
    int calcPrice(Basket basket);
    int calcPriceWithoutDiscount(Basket basket);

    void resetDiscount(Basket basket);
}
