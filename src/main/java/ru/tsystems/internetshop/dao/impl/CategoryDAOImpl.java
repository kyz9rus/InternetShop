package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.CategoryDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Category;

import javax.persistence.TypedQuery;

/**
 * This class extends AbstractDAO, implements CategoryDAO and override its methods
 */
@Repository
public class CategoryDAOImpl extends AbstractDAO<Category, Long> implements CategoryDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Override
    public Category createAndGet(Category category) {
        try {
            getSession().persist(category);

            return category;
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    /**
     * This method updates Category entity in database using HQL
     * @param oldName old category name
     * @param category new category
     */
    @Override
    public void updateCategory(String oldName, Category category) {
        try {
            String queryString = "UPDATE category c set c.name = :name where c.name = :oldName";

            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("name", category.getName());
            query.setParameter("oldName", oldName);

            query.executeUpdate();
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    /**
     * This method get category by name
     * @param name name of category (unique)
     * @return category
     */
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
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    /**
     * This method delete category by name
     * @param categoryName name of category
     */
    @Override
    public void deleteByName(String categoryName) {
        try {
            String queryString = "DELETE category c where c.name = :name";

            Query query = sessionFactory.getCurrentSession().createQuery(queryString);
            query.setParameter("name", categoryName);

            query.executeUpdate();
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }
}