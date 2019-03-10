package ru.tsystems.internetshop.model;

import lombok.Data;

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
    private Set<ProductDto> products = new HashSet<>();
}
