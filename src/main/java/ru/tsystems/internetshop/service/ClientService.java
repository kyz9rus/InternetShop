package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientDTO;

import java.util.List;

public interface ClientService {
    void saveClient(ClientDTO clientDTO);

    void updateClient(ClientDTO client);

    ClientDTO getClientByEmail(String email);

    List<ClientDTO> getTop10Clients();

    List<ClientDTO> getTop10ClientsWithAtLeast1Order();
}
