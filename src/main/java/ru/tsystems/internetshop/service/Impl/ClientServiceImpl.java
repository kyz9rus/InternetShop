package ru.tsystems.internetshop.service.Impl;

import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.service.ClientService;

@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public void saveClient(Client client) {
        clientDAO.saveClient(client);
    }

    public void updateClient(Client client) {clientDAO.updateClient(client);}

    public Client getClientByEmail(String email) {
        return clientDAO.findByEmail(email);
    }
}