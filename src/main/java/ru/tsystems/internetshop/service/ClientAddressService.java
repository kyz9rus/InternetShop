package ru.tsystems.internetshop.service;

import org.springframework.ui.Model;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import java.util.List;
import java.util.Set;

/**
 * This is interface, which declares methods for managing client addresses
 */
public interface ClientAddressService {
    Model updateClientAddress(ClientAddressDTO clientAddressDTO, Model model);

    ClientAddressDTO getClientAddressById(Long id);

    List<ClientAddressDTO> getAddressesByClient(ClientDTO clientDTO);

    Model deleteClientAddress(ClientAddressDTO clientAddressDTO, Model model);

    Model createAddress(ClientDTO clientDTO, ClientAddressDTO clientAddressDTO, Model model);
}
