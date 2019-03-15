package ru.tsystems.internetshop.model.entity;

import lombok.Data;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.DTO.ColorDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.DTO.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "product")
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(unique = true, length = 70)
    private String name;

    @NotNull
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_name")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_color",
            joinColumns =  @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "color", referencedColumnName = "name")
    )
    private Set<Color> colors = new HashSet<>();

    private double weight;

    @Size(min = 11, max = 11)
    private String volume;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns  =  @JoinColumn(name = "order_id", referencedColumnName = "id")
    )
    private Set<Order> orders = new HashSet<>();

    @NotNull
    private long quantityInStock;

    public Product() {
    }

    public Product(@NotNull String name, @NotNull int price, double weight, String volume, @NotNull long quantityInStock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.quantityInStock = quantityInStock;
    }

//    @Override
//    public ProductDTO clone() throws CloneNotSupportedException {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setId(id);
//        productDTO.setName(name);
//        productDTO.setPrice(price);
//        productDTO.setWeight(weight);
//        productDTO.setVolume(volume);
//        productDTO.setQuantityInStock(quantityInStock);
//        productDTO.setCategory(category.clone());
//
//        Set<ColorDTO> colorDTOS = new HashSet<>();
//        for (Color color : colors)
//            colorDTOS.add(color.clone());
//        productDTO.setColors(colorDTOS);
//
//        Set<OrderDTO> orderDTOS = new HashSet<>();
//        for (Order order : orders)
//            orderDTOS.add(order.clone());
//        productDTO.setOrders(orderDTOS);
//
//        return productDTO;
//    }
}
