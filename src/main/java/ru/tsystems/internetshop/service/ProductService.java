package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import java.util.List;

/**
 * This is interface, which declares methods for managing products
 */
public interface ProductService {
    void saveProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    ProductDTO getProductByName(String name);

    List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO);

    ProductDTO getProduct(Long id);

    List<ProductDTO> getTop10Products();
}
