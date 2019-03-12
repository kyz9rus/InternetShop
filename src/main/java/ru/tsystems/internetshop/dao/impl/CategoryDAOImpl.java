package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.model.entity.Category;

@Repository
public class CategoryDAOImpl extends AbstractDAO<Category, String> implements CategoryDAO {
}