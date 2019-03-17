package ru.tsystems.internetshop.model.DTO;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", colors:" + colors.size() +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", orders:" + orders.size() +
                ", quantityInStock=" + quantityInStock +
                '}';
    }
}
