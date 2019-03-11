package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.Client;

public interface ClientService {
    void saveClient(Client client);

    void updateClient(Client client);

    Client getClientByEmail(String email);
}
