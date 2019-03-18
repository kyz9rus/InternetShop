package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories ();

    void updateCategory(String oldName, CategoryDTO categoryDTO);

    void removeCategory(CategoryDTO categoryDTO);
}
