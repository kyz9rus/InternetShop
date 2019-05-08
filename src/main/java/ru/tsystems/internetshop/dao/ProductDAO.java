package ru.tsystems.internetshop.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product, Long> {
    List<Product> findProductsByCategory(Category category);

    Product findProductByName(String name);

    List<Product> findTop10Products();

//    Page<Product> findProductsByCategory2(Pageable pageable);
}