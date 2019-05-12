package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.List;

/**
 * This interface extends DAO interface and declares special methods for Product class
 */
public interface ProductDAO extends DAO<Product, Long> {
    List<Product> findProductsByCategory(Category category);

    Product findProductByName(String name);

    List<Product> findTop10Products();
}