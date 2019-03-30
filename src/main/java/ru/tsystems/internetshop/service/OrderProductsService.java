package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.OrderProductDTO;

public interface OrderProductsService {
    void saveOrderProduct(OrderProductDTO orderProductDTO);
}
