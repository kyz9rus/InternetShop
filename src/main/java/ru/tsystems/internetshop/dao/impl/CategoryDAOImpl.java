package ru.tsystems.internetshop.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Category;

import javax.persistence.TypedQuery;

@Repository
public class CategoryDAOImpl extends AbstractDAO<Category, Long> implements CategoryDAO {

    @Override
    public void updateCategory(String oldName, Category category) {
        try {
            String queryString = "UPDATE category c set c.name = :name where c.name = :oldName";

            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("name", category.getName());
            query.setParameter("oldName", oldName);

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public Category findByName(String name) {
        try {
            String queryString = "SELECT c FROM category c where c.name = :name";

            TypedQuery<Category> query = sessionFactory.getCurrentSession().createQuery(queryString, Category.class);
            query.setParameter("name", name);

            if (!query.getResultList().isEmpty())
                return query.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public void deleteByName(String categoryName) {
        try {
            String queryString = "DELETE category c where c.name = :name";

            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("name", categoryName);

            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}