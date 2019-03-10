package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "product")
@Table(name = "product")
public class ProductDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(unique = true, length = 70)
    private String name;

    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
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
    private String volume;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns  =  @JoinColumn(name = "order_id", referencedColumnName = "id")
    )
    private Set<OrderDto> orders = new HashSet<>();

    @NotNull
    private long quantityInStock;

    public ProductDto() {
    }

    public ProductDto(@NotNull String name, @NotNull int price, double weight, String volume, @NotNull long quantityInStock) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.volume = volume;
        this.quantityInStock = quantityInStock;
    }
}
