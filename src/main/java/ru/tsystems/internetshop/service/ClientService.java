package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.DTO.ClientDTO;

import java.util.List;

/**
 * This is interface, which declares methods for managing clients
 */
public interface ClientService {
    void saveClient(ClientDTO clientDTO);

    void updateClient(ClientDTO clientDTO);

    Model updateClient(String email, ClientDTO clientDTO, Model model);

    ClientDTO getClientByEmail(String email);

    List<ClientDTO> getTop10Clients();

    List<ClientDTO> getTop10ClientsWithAtLeast1Order();
}
