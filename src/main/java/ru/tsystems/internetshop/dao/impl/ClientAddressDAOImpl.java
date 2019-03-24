package ru.tsystems.internetshop.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ClientAddressDAO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Repository
public class ClientAddressDAOImpl extends AbstractDAO<ClientAddress, Long> implements ClientAddressDAO {
    @Override
    public List<ClientAddress> getAddressesByClient(Client client) {
        String queryString = "SELECT a FROM clientAddress a WHERE a.client = :client";

        TypedQuery<ClientAddress> query = sessionFactory.getCurrentSession().createQuery(queryString, ClientAddress.class);
        query.setParameter("client", client);

        return query.getResultList();
    }
}
