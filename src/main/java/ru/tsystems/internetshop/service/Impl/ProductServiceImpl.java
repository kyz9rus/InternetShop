package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.service.ProductService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This is class, which implements methods from ProductService
 */
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private Mapper mapper;

    /**
     * This method saves product
     * @param productDTO product
     */
    @Override
    public void saveProduct(ProductDTO productDTO) {
        productDAO.create(mapper.convertToEntity(productDTO));
    }

    /**
     * This method updates product
     * @param productDTO product
     */
    @Override
    public void updateProduct(ProductDTO productDTO) {
        productDAO.update(mapper.convertToEntity(productDTO));
    }

    /**
     * This method gets product by name
     *
     * @param name product name
     * @return product or null (if it doesn't exist)
     */
    @Override
    public ProductDTO getProductByName(String name) {
        try {
            return mapper.convertToDto(productDAO.findProductByName(name));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * This method gets product by category
     *
     * @param categoryDTO category
     * @return list of products
     */
    @Override
    public List<ProductDTO> getProductsByCategory(CategoryDTO categoryDTO) {
        List<Product> products = productDAO.findProductsByCategory(mapper.convertToEntity(categoryDTO));
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products)
            productDTOS.add(mapper.convertToDto(product));

        return productDTOS;
    }

    /**
     * This method gets product by id
     *
     * @param id product id
     * @return product
     */
    @Override
    public ProductDTO getProduct(Long id) {
        return mapper.convertToDto(productDAO.findByKey(id));
    }

    /**
     * This method gets top 10 products
     *
     * @return list of products
     */
    @Override
    public List<ProductDTO> getTop10Products() {
        List<Product> products = productDAO.findTop10Products();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products)
            productDTOS.add(mapper.convertToDto(product));

        return productDTOS;
    }
}
