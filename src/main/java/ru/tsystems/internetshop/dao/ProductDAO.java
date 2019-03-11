package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.Product;

import java.util.List;

public interface ProductDAO {
    Product findByName(String name);

    void saveProduct(Product product);

    List<Product> findProductsByCategory(Category category);
}