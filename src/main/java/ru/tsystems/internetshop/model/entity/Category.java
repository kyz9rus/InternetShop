package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "category")
@Table(name = "category")
public class Category {

    @Id
    @Column(length = 70, name = "name")
    private String name;

    @OneToMany(mappedBy = "category", targetEntity = Product.class)
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

//    @Override
//    public CategoryDTO clone() throws CloneNotSupportedException {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setName(name);
//
//        Set<ProductDTO> productDTOS = new HashSet<>();
//        for (Product product : products)
//            productDTOS.add(product.clone());
//        categoryDTO.setProducts(productDTOS);
//
//        return categoryDTO;
//    }
}
