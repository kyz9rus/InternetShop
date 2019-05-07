package ru.tsystems.internetshop.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystems.internetshop.dao.AbstractDAO;
import ru.tsystems.internetshop.dao.ClientAddressDAO;
import ru.tsystems.internetshop.exception.DAOException;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ClientAddressDAOImpl extends AbstractDAO<ClientAddress, Long> implements ClientAddressDAO {

    private final Logger fileLogger = Logger.getLogger("fileLogger");

    @Override
    public List<ClientAddress> findAddressesByClient(Client client) {
        try {
            String queryString = "SELECT a FROM clientAddress a WHERE a.client = :client";

            TypedQuery<ClientAddress> query = sessionFactory.getCurrentSession().createQuery(queryString, ClientAddress.class);
            query.setParameter("client", client);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            fileLogger.error(e.getMessage());
            throw new DAOException();
        }
    }
}
