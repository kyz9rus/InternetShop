package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.DTO.CategoryDTO;
import ru.tsystems.internetshop.model.DTO.OrderDTO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.service.CategoryService;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.CategoryInfo;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This is class, which implements methods from CategoryService
 */
@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private Mapper mapper;

    @Autowired
    private CategoryInfo categoryInfo;

    @Autowired
    private OrderService orderService;

    /**
     * This methods gets all categories
     * @return list of categories
     */
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        List<Category> categories = categoryDAO.findAll();

        for (Category category : categories)
            categoryDTOS.add(mapper.convertToDto(category));

        return categoryDTOS;
    }

    /**
     * This method gets category by name
     * @param name name of category
     * @return category or null (if it doesn't exist)
     */
    @Override
    public CategoryDTO getCategoryByName(String name) {
        try {
            return mapper.convertToDto(categoryDAO.findByName(name));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Model removeCategory(String categoryName, Model model) {
        CategoryDTO categoryDTO = getCategoryByName(categoryName);
        List<OrderDTO> orders = orderService.getOrdersByCategory(categoryDTO);

        if (orders.isEmpty()) {
            categoryName = categoryName.toLowerCase();

            removeCategoryByName(categoryName);

            categoryInfo.getCategories().clear();

            List<CategoryDTO> categoryDTOS = getAllCategories();
            categoryDTOS.forEach(category -> category.setName(category.getName().replaceAll("_", " ").toUpperCase()));

            categoryInfo.getCategories().addAll(categoryDTOS);

            model.addAttribute("successMessage", "Category successfully changed.");
        } else {
            model.addAttribute("errorMessage", "Category cannot be removed (There are incomplete orders)");
        }

        return model;
    }

    /**
     * This method saves a new category
     * @param categoryDTO category
     */
    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return mapper.convertToDto(categoryDAO.createAndGet(mapper.convertToEntity(categoryDTO)));
    }

    /**
     * This method updates category in database
     * @param oldName name of category
     * @param categoryDTO new category
     */
    @Override
    public void updateCategory(String oldName, CategoryDTO categoryDTO) {
        categoryDAO.updateCategory(oldName, mapper.convertToEntity(categoryDTO));
    }

    /**
     * This method deletes category by name
     * @param categoryName name of category
     */
    @Override
    public void removeCategoryByName(String categoryName) {
        categoryDAO.deleteByName(categoryName);
    }
}