package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    private Long id;

    @NotNull
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }
}
