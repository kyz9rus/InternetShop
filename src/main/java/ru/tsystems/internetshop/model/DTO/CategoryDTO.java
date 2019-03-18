package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CategoryDTO {
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }
}
