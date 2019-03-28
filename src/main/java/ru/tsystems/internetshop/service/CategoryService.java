package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDTO categoryDTO);

    void updateCategory(String oldName, CategoryDTO categoryDTO);

    void removeCategoryByName(String categoryName);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
