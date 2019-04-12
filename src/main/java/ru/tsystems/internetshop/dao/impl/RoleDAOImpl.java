package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.OrderDAO;
import ru.tsystems.internetshop.dao.RoleDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Order;
import ru.tsystems.internetshop.model.entity.Product;
import ru.tsystems.internetshop.model.entity.Role;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl extends AbstractDAO<Role, Long> implements RoleDAO {
    @Override
    public Role findByName(String name) {
        try {
            String queryString = "SELECT r FROM role r WHERE r.name = :name";

            TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery(queryString, Role.class);
            query.setParameter("name", name);

            List<Role> roles = query.getResultList();

            if (!roles.isEmpty())
                return roles.get(0);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
