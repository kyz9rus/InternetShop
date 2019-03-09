package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.ProductDto;

public interface ProductDAO {
    ProductDto findByName(String name);

    void saveProduct(ProductDto product);
}