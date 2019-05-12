package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;

import java.util.List;

/**
 * This interface extends DAO interface and declares special methods for ClientAddress class
 */
public interface ClientDAO extends DAO<Client, Long> {
    Client findByEmail(String email);

    List<Client> findTop10Clients();

    List<Client> findTop10ClientsWithAtLeast1Order();
}