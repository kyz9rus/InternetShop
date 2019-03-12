package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.ProductService;

import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void saveProduct(Product product) {
        productDAO.create(product);
    }

    public Product getProductByName(String name) {
        return productDAO.findProductByName(name);
    }

    public List<Product> getProductsByCategory(Category category) {
        return productDAO.findProductsByCategory(category);
    }
}
