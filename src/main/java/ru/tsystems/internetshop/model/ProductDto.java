package ru.tsystems.internetshop.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String name;
    private int price;
    private Category category;
    private List<Parameter> parameters;
    private double weight;
    private String volume;
    private long quantityInStock;
}
