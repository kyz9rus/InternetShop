package ru.tsystems.internetshop.model;

import lombok.Data;

@Data
public class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
