package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public void saveProduct(ProductDTO productDTO) {
        productDAO.create(mapper.convertToEntity(productDTO));
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productDAO.update(mapper.convertToEntity(productDTO));
    }

    @Override
    public ProductDTO getProductByName(String name) {
        return mapper.convertToDto(productDAO.findProductByName(name));
    }

    @Override
    public List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO) {
        List<Product> products = productDAO.findProductsByCategory(mapper.convertToEntity(categoryDTO));
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products)
            productDTOS.add(mapper.convertToDto(product));

        return productDTOS;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return mapper.convertToDto(productDAO.findByKey(id));
    }

    @Override
    public List<ProductDTO> getTop10Products() {
        List<Product> products = productDAO.getTop10Products();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products)
            productDTOS.add(mapper.convertToDto(product));

        return productDTOS;
    }
}
