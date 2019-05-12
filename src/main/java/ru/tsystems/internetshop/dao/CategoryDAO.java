package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Category;

/**
 * This interface extends DAO interface and declares special methods for Category class
 */
public interface CategoryDAO extends DAO<Category, Long> {
    void updateCategory(String oldName, Category category);

    Category findByName(String name);

    void deleteByName(String categoryName);
}