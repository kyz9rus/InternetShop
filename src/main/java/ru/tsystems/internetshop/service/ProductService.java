package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    void saveProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    ProductDTO getProductByName(String name);

    List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO);
//    Page<ProductDTO> getProductsByCategory2(Pageable pageable, CategoryDTO categoryDTO);

    ProductDTO getProduct(Long id);

    List<ProductDTO> getTop10Products();
}
