package ru.tsystems.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.ProductDto;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void saveProduct(ProductDto product) {
        productDAO.saveProduct(product);
    }

    public ProductDto getProductByName(String name) {
        return productDAO.findByName(name);
    }

}
