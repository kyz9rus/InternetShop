package ru.tsystems.internetshop.util;

import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.model.Category;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryInfo {
    private List<Category> categories;

    private CategoryInfo() {
        categories = new ArrayList<>();
    }

    public List<Category> getInstance() {
        if (categories == null)
            categories = new ArrayList<>();

        return categories;
    }
}
