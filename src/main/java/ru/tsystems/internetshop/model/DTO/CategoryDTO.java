package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDTO {
    private String name;
    private Set<ProductDTO> products = new HashSet<>();

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "name='" + name + '\'' +
                ", products:" + products.size() +
                '}';
    }
}
