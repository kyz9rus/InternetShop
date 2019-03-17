package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    Product getProductByName(String name);

    List<Product> getProductsByCategory(Category category);
}
