package ru.tsystems.internetshop.model.DTO;

import lombok.Data;
import ru.tsystems.internetshop.model.entity.Category;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This class is DTO for category entity
 */
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
        CategoryDTO categoryDTO = (CategoryDTO) o;
        return Objects.equals(name, categoryDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
