package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;

public interface CategoryDAO extends DAO<Category, Long> {
    void updateCategory(String oldName, Category category);

    Category findByName(String name);

    void deleteByName(String categoryName);
}