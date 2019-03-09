package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "product")
//@Entity
@Table(name = "product")
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(unique = true, length = 70)
    private String name;

    @NotNull
    private int price;

//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Category.class)
//    @JoinColumn(name = "category_name")
//    private Category category;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "product_color",
//            joinColumns =  @JoinColumn(name = "product_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "color", referencedColumnName = "name")
//    )
//    private Set<Color> colors = new HashSet<>();

    private double weight;
    private String volume;

    @NotNull
    private long quantityInStock;

    public ProductDto() {
    }

}
