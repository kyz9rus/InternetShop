package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDTO categoryDTO);

    void updateCategory(String oldName, CategoryDTO categoryDTO);

    void removeCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategory(String name);
}
