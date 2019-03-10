package ru.tsystems.internetshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "category")
//@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(length = 70, name = "name")
    private String name;

    @OneToMany(mappedBy = "category", targetEntity = ProductDto.class, fetch = FetchType.EAGER)
    private Set<ProductDto> products = new HashSet<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
