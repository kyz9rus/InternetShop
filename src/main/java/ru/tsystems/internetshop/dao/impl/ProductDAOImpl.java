package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.model.Category;
import ru.tsystems.internetshop.model.ProductDto;

import javax.persistence.TypedQuery;
import java.util.List;


@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ProductDto findByName(String name) {
        String queryString = "SELECT p FROM product p WHERE p.name = :name";

        TypedQuery<ProductDto> query = sessionFactory.getCurrentSession().createQuery(queryString, ProductDto.class);
        query.setParameter("name", name);

        List<ProductDto> products = query.getResultList();

        if (!products.isEmpty())
            return products.get(0);
        else
            return null;
    }

    @Override
    public void saveProduct(ProductDto product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public List<ProductDto> findProductsByCategory(Category category) {
        String queryString = "SELECT p FROM product p WHERE p.category.name = :category_name";

        TypedQuery<ProductDto> query = sessionFactory.getCurrentSession().createQuery(queryString, ProductDto.class);
        query.setParameter("category_name", category.getName().toLowerCase());

        return query.getResultList();
    }
}
