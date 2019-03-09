package ru.tsystems.internetshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ClientDto;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
}