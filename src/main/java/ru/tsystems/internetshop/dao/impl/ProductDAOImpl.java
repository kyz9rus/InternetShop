package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.Product;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductDAOImpl extends AbstractDAO<Product, Long> implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveProduct(Product product) {
        create(product);
    }

    @Override
    public Product findByName(String name) {
        String queryString = "SELECT p FROM product p WHERE p.name = :name";

        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(queryString, Product.class);
        query.setParameter("name", name);

        List<Product> products = query.getResultList();

        if (!products.isEmpty())
            return products.get(0);
        else
            return null;
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        String queryString = "SELECT p FROM product p WHERE p.category.name = :category_name";

        TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(queryString, Product.class);
        query.setParameter("category_name", category.getName().toLowerCase());

        return query.getResultList();
    }
}
