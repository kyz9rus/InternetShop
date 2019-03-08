package ru.tsystems.internetshop.service;

import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

//    public ClientDto findById(int id) {
//        return clientDAO.findById(id);
//    }

    public void saveClient(ClientDto client) {
        clientDAO.saveClient(client);
    }

//    public void updateClient(ClientDto client) {
//        // TODO Auto-generated method stub
//        ClientDto entity = clientDAO.findById(client.getId());
//        if (entity != null) {
//            entity.setFirstName(client.getFirstName());
//            entity.setLastName(client.getLastName());
//            entity.setBirthday(client.getBirthday());
//            entity.setEmail(client.getEmail());
//            entity.setPassword(client.getPassword());
//        }
//
//    }

//    public List<ClientDto> findAllClients() {
//        return clientDAO.findAllClients();
//    }
//
//    public ClientDto findEmployeeByEmail(String email) {
//        return clientDAO.findByEmail(email);
//    }
}