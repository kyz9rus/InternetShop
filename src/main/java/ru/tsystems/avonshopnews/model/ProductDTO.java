package ru.tsystems.avonshopnews.model;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private int price;
    private Double weight;
    private String volume;
    private Long quantityInStock;
    private String imgSrc;
    private Long numberOfSales;

    private static final long serialVersionUID = 1;
}
