package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private int price;
    private CategoryDTO category;
    private Set<ColorDTO> colors = new HashSet<>();
    private double weight;
    private String volume;
    private Set<OrderDTO> orders = new HashSet<>();
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
