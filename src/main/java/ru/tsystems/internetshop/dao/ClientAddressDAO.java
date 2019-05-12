package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import java.util.List;

/**
 * This interface extends DAO interface and declares special methods for ClientAddress class
 */
public interface ClientAddressDAO extends DAO<ClientAddress, Long> {
    List<ClientAddress> findAddressesByClient(Client client);
}