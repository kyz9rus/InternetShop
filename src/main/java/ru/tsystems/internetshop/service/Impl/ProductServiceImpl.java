package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;
import ru.tsystems.internetshop.model.DTO.ProductPageDTO;
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
        try {
            return mapper.convertToDto(productDAO.findProductByName(name));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

//    @Override
//    public ProductPageDTO getProductsByCategory2(Pageable pageable, CategoryDTO categoryDTO) {
//        List<Product> products = productDAO.findProductsByCategory(mapper.convertToEntity(categoryDTO));
//        List<ProductDTO> productDTOS = new ArrayList<>();
//
//        for (Product product : products)
//            productDTOS.add(mapper.convertToDto(product));
//
//        Page<ProductDTO> page = productDAO.findProductsByCategory();
//        return null;
//    }

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
        List<Product> products = productDAO.findTop10Products();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products)
            productDTOS.add(mapper.convertToDto(product));

        return productDTOS;
    }
}
