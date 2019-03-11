package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();

    Category findByName(String name);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);
}