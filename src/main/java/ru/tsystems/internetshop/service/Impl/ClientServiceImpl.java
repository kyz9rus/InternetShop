package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.util.Mapper;

@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private Mapper mapper;

    public void saveClient(ClientDTO clientDTO) {
        clientDAO.create(mapper.convertToEntity(clientDTO));
    }

    public void updateClient(ClientDTO clientDTO) {
        clientDAO.update(mapper.convertToEntity(clientDTO));
    }

    public ClientDTO getClientByEmail(String email) {
        try {
            return mapper.convertToDto(clientDAO.findByEmail(email));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}