package ru.tsystems.internetshop.model.entity;

import lombok.Data;

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
}