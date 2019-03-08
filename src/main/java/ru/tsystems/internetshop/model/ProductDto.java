package ru.tsystems.internetshop.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private Category category;
    private List<Parameter> parameters;// many_to_many
    private double weight;
    private String volume;
    private long quantityInStock;

    public ProductDto(String name, int price, Category category, List<Parameter> parameters, double weight, String volume, long quantityInStock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.parameters = parameters;
        this.weight = weight;
        this.volume = volume;
        this.quantityInStock = quantityInStock;
    }
}
