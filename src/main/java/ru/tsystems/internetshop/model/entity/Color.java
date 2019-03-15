package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.model.DTO.ColorDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "color")
@Table(name = "color")
public class Color {

    @Id
    @Column(length = 70, name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_color",
        joinColumns = @JoinColumn(name = "color", referencedColumnName = "name"),
        inverseJoinColumns =  @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Product> products = new HashSet<>();

//    @Override
//    public ColorDTO clone() throws CloneNotSupportedException {
//        ColorDTO colorDTO = new ColorDTO();
//        colorDTO.setName(name);
//
//        Set<ProductDTO> productDTOS = new HashSet<>();
//        for (Product product : products)
//            productDTOS.add(product.clone());
//        colorDTO.setProducts(productDTOS);
//
//        return colorDTO;
//    }
}
