package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ProductDto;

import java.util.List;

public interface ProductDAO {
    ProductDto findByName(String name);

    void saveProduct(ProductDto product);

    List<ProductDto> findProductsByCategory(Category category);
}