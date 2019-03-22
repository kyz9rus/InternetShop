package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;

public interface ClientService {
    void saveClient(ClientDTO clientDTO);

    void updateClient(ClientDTO client);

    ClientDTO getClientByEmail(String email);
}
