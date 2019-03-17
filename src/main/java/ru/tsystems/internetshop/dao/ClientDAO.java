package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;

public interface  ClientDAO extends DAO<Client, Long> {
    Client findByEmail(String email);
}