package ru.tsystems.internetshop.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Product;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CategoryDAOImpl extends AbstractDAO<Category, String> implements CategoryDAO {

    @Override
    public void updateCategory(String oldName, Category category) {
        String queryString = "UPDATE category c set c.name = :name where c.name = :oldName";

//        TypedQuery<Category> query = sessionFactory.getCurrentSession().createQuery(queryString, Category.class);
        Query query = sessionFactory.getCurrentSession().createQuery(queryString);
        query.setParameter("name", category.getName());
        query.setParameter("oldName", oldName);

        query.executeUpdate();
    }
}