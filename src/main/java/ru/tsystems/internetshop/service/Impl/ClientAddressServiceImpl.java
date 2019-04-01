package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientAddressDAO;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.service.ClientAddressService;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class ClientAddressServiceImpl implements ClientAddressService {

    @Autowired
    private ClientAddressDAO clientAddressDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public void saveClientAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.create(mapper.convertToEntity(clientAddressDTO));
    }

    @Override
    public void updateClientAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.update(mapper.convertToEntity(clientAddressDTO));
    }

    @Override
    public ClientAddressDTO getClientAddressById(Long id) {
        try {
            return mapper.convertToDto(clientAddressDAO.findByKey(id));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw null;
        }
    }

    @Override
    public List<ClientAddressDTO> getAddressesByClient(ClientDTO clientDTO) {
        List<ClientAddress> clientAddresses = clientAddressDAO.getAddressesByClient(mapper.convertToEntity(clientDTO));
        List<ClientAddressDTO> clientAddressDTOS = new ArrayList<>();

        for (ClientAddress clientAddress : clientAddresses)
            clientAddressDTOS.add(mapper.convertToDto(clientAddress));

        return clientAddressDTOS;
    }

    @Override
    public void deleteAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.delete(mapper.convertToEntity(clientAddressDTO));
    }
}