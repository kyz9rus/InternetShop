package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    Product getProductByName(String name);

    List<Product> getProductsByCategory(Category category);
}
