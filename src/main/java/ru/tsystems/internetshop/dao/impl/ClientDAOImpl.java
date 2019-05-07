package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Client;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientDAOImpl extends AbstractDAO<Client, Long> implements ClientDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Override
    public Client findByEmail(String email) {
        try {
            String queryString = "SELECT p FROM client p WHERE p.email = :email";

            TypedQuery<Client> query = sessionFactory.getCurrentSession().createQuery(queryString, Client.class);
            query.setParameter("email", email);

            List<Client> clients = query.getResultList();

            if (!clients.isEmpty())
                return clients.get(0);
            else
                return null;
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }

    @Override
    public List<Client> findTop10Clients() {
        try{
        String queryString = "SELECT c FROM client c ORDER BY c.summaryOrdersPrice DESC";

        TypedQuery<Client> query = sessionFactory.getCurrentSession().createQuery(queryString, Client.class);

        return query.getResultList().stream().limit(10).collect(Collectors.toList());
        } catch (Exception e) {
            fileLogger.error(e.getMessage());
            e.printStackTrace();
            throw new DAOException();
        }
    }
}
