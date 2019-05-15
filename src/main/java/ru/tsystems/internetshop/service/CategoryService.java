package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;

import java.util.List;

/**
 * This is interface, which declares methods for managing categories
 */
public interface CategoryService {
    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    void updateCategory(String oldName, CategoryDTO categoryDTO);

    void removeCategoryByName(String categoryName);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);

    Model removeCategory(String categoryName, Model model);
}
