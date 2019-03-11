package ru.tsystems.internetshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.Client;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientDAOImpl extends AbstractDAO<Client, Integer> implements ClientDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client findByEmail(String email) {
        String queryString = "SELECT p FROM client p WHERE p.email = :email";

        TypedQuery<Client> query = sessionFactory.getCurrentSession().createQuery(queryString, Client.class);
        query.setParameter("email", email);

        List<Client> clients = query.getResultList();

        if (!clients.isEmpty())
            return clients.get(0);
        else
            return null;
    }

    @Override
    public void saveClient(Client client) {
        create(client);
    }

    @Override
    public void updateClient(Client client) {
        update(client);
    }
}
