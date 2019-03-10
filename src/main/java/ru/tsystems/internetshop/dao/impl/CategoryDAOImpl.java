package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ClientDto;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Transactional
@Repository
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
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
