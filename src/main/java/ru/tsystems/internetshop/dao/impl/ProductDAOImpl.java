package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ProductDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Category;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Product;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDAOImpl extends AbstractDAO<Product, Long> implements ProductDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Override
    public List<Product> findProductsByCategory(Category category) {
        try {
            String queryString = "SELECT p FROM product p WHERE p.category.name = :category_name";

            TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(queryString, Product.class);
            query.setParameter("category_name", category.getName().toLowerCase());

            return query.getResultList();
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public Product findProductByName(String name) {
        try {
            String queryString = "SELECT p FROM product p WHERE p.name = :name";

            TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(queryString, Product.class);
            query.setParameter("name", name);

            List<Product> products = query.getResultList();

            if (!products.isEmpty())
                return products.get(0);
            else
                return null;
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<Product> findTop10Products() {
        try {
            String queryString = "SELECT p FROM product p ORDER BY p.numberOfSales DESC";

            TypedQuery<Product> query = sessionFactory.getCurrentSession().createQuery(queryString, Product.class);

            return query.getResultList().stream().limit(10).collect(Collectors.toList());
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

//    @Override
//    public Page<Product> findProductsByCategory2(Pageable pageable) {
//        try {
//            String queryString = "";
//
//            Specification<Todo> searchSpec = titleOrDescriptionContainsIgnoreCase(searchTerm);
//            Page<Todo> searchResultPage = repository.findAll(searchSpec, pageRequest);
//
//            return
//        } catch (Exception e) {
//            fileLogger.error(e.getMessage());
//            e.printStackTrace();
//            throw new DAOException();
//        }
//    }
}
