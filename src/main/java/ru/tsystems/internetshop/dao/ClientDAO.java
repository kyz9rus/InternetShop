package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.Client;

public interface ClientDAO {
    void saveClient(Client client);

    void updateClient(Client client);

    Client findByEmail(String email);
}