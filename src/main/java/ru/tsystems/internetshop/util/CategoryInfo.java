package ru.tsystems.internetshop.util;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.service.CategoryService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class contains set of categories, which is showed in pages
 */
public class CategoryInfo implements Serializable {

    @Autowired
    private CategoryService categoryService;

    private Set<CategoryDTO> categories;

    public CategoryInfo() {
        categories = new HashSet<>();
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    @PostConstruct
    public void init() {
        categories = new HashSet<>(categoryService.getAllCategories());
    }
}