package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO productDTO);

    ProductDTO getProductByName(String name);

    List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO);

    ProductDTO getProduct(Long id);
}
