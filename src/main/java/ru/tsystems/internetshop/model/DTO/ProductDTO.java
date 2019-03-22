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
    private String imgSrc;
}
