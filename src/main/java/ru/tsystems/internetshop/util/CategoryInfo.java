package ru.tsystems.internetshop.util;

import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryInfo {
    private List<CategoryDTO> categories;

    private CategoryInfo() {
        categories = new ArrayList<>();
    }

    public List<CategoryDTO> getInstance() {
        if (categories == null)
            categories = new ArrayList<>();

        return categories;
    }
}