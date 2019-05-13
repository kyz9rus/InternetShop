package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientAddressDAO;
import ru.tsystems.internetshop.model.DTO.ClientAddressDTO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.ClientAddress;
import ru.tsystems.internetshop.service.ClientAddressService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This is class, which implements methods from ClientAddressesService
 */
@Transactional
@Service
public class ClientAddressServiceImpl implements ClientAddressService {

    @Autowired
    private ClientAddressDAO clientAddressDAO;

    @Autowired
    private Mapper mapper;

    /**
     * This method saves client address
     *
     * @param clientAddressDTO client address
     */
    @Override
    public void saveClientAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.create(mapper.convertToEntity(clientAddressDTO));
    }

    /**
     * This method updates client address
     *
     * @param clientAddressDTO client address
     */
    @Override
    public void updateClientAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.update(mapper.convertToEntity(clientAddressDTO));
    }

    /**
     * This method gets client address by id
     *
     * @param id client address id
     * @return client address or null (if it doesn't exist)
     */
    @Override
    public ClientAddressDTO getClientAddressById(Long id) {
        try {
            return mapper.convertToDto(clientAddressDAO.findByKey(id));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw null;
        }
    }

    /**
     * This method gets client addresses for the client
     *
     * @param clientDTO client
     * @return client address
     */
    @Override
    public List<ClientAddressDTO> getAddressesByClient(ClientDTO clientDTO) {
        List<ClientAddress> clientAddresses = clientAddressDAO.findAddressesByClient(mapper.convertToEntity(clientDTO));
        List<ClientAddressDTO> clientAddressDTOS = new ArrayList<>();

        for (ClientAddress clientAddress : clientAddresses)
            clientAddressDTOS.add(mapper.convertToDto(clientAddress));

        return clientAddressDTOS;
    }

    /**
     * This method deletes address
     *
     * @param clientAddressDTO client address
     */
    @Override
    public void deleteAddress(ClientAddressDTO clientAddressDTO) {
        clientAddressDAO.delete(mapper.convertToEntity(clientAddressDTO));
    }
}