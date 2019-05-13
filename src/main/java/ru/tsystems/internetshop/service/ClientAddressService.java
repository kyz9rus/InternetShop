package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import java.util.List;
import java.util.Set;

/**
 * This is interface, which declares methods for managing client addresses
 */
public interface ClientAddressService {
    void saveClientAddress(ClientAddressDTO clientAddressDTO);

    void updateClientAddress(ClientAddressDTO clientAddressDTO);

    ClientAddressDTO getClientAddressById(Long id);

    List<ClientAddressDTO> getAddressesByClient(ClientDTO clientDTO);

    void deleteAddress(ClientAddressDTO clientAddressDTO);
}
