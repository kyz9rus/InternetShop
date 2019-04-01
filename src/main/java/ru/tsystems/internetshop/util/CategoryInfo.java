package ru.tsystems.internetshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.service.CategoryService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryInfo implements Serializable {

    @Autowired
    private CategoryService categoryService;

    private List<CategoryDTO> categories;

    public CategoryInfo() {
        categories = new ArrayList<>();
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    @PostConstruct
    public void init() {
        categories = categoryService.getAllCategories();
    }
}