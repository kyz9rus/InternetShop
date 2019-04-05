package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;

import java.util.List;

public interface ClientDAO extends DAO<Client, Long> {
    Client findByEmail(String email);

    List<Client> findTop10Clients();
}