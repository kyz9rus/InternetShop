package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Basket;

public interface BasketService {
    int calcPrice(Basket basket);
}