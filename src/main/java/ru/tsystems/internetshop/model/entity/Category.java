package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is category entity
 */
@Entity(name = "category")
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 70, name = "name", unique = true)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
