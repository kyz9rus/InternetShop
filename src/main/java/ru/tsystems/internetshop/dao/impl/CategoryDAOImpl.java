package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.Category;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAOImpl extends AbstractDAO<Category, String> implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCategory(Category category) {
        create(category);
    }

    @Override
    public void updateCategory(Category category) {
        update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        delete(category);
    }

    @Override
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from category").getResultList();
    }

    @Override
    public Category findByName(String name) {
        @SuppressWarnings("unchecked")
        TypedQuery<Category> query = sessionFactory.getCurrentSession().createQuery("from category");

        List<Category> categories = query.getResultList();

        Optional<Category> category = categories.stream().filter(tempCategory -> tempCategory.getName().equals(name)).findAny();

        return category.orElse(null);
    }
}
