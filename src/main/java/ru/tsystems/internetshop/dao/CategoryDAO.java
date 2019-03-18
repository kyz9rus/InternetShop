package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;

public interface CategoryDAO extends DAO<Category, String>{
    void updateCategory(String oldName, Category category);
}