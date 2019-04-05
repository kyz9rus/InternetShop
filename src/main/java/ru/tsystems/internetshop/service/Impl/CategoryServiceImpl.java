package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private Mapper mapper;

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        List<Category> categories = categoryDAO.findAll();

        for (Category category : categories)
            categoryDTOS.add(mapper.convertToDto(category));

        return categoryDTOS;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        try {
            return mapper.convertToDto(categoryDAO.findByName(name));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public void saveCategory(CategoryDTO categoryDTO) {
        categoryDAO.create(mapper.convertToEntity(categoryDTO));
    }

    @Override
    public void updateCategory(String oldName, CategoryDTO categoryDTO) {
        categoryDAO.updateCategory(oldName, mapper.convertToEntity(categoryDTO));
    }

    @Override
    public void removeCategoryByName(String categoryName) {
        categoryDAO.deleteByName(categoryName);
    }
}