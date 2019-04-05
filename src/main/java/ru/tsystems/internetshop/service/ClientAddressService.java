package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import java.util.List;
import java.util.Set;

public interface ClientAddressService {
    void saveClientAddress(ClientAddressDTO clientAddressDTO);

    void updateClientAddress(ClientAddressDTO clientAddressDTO);

    ClientAddressDTO getClientAddressById(Long id);

    List<ClientAddressDTO> getAddressesByClient(ClientDTO clientDTO);

    void deleteAddress(ClientAddressDTO clientAddressDTO);
}
