package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity(name = "category")
@Table(name = "category")
public class Category {

    @Id
    @Column(length = 70, name = "name")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<Product> productList = new ArrayList<>();

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
}
