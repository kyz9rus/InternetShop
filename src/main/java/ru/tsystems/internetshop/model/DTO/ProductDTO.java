package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class ProductDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;
    private CategoryDTO category;
    private List<ColorDTO> colors = new ArrayList<>();
    private double weight;

    @Size(min = 11, max = 11)
    private String volume;
    private List<OrderDTO> orders = new ArrayList<>();

    @NotNull
    private long quantityInStock;
    private String imgSrc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
