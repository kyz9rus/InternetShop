package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

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
        return price == that.price &&
                Double.compare(that.weight, weight) == 0 &&
                quantityInStock == that.quantityInStock &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(imgSrc, that.imgSrc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, weight, volume, quantityInStock, imgSrc);
    }
}
