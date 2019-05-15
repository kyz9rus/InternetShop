package ru.tsystems.internetshop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.tsystems.internetshop.dao.ClientDAO;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.OrderService;
import ru.tsystems.internetshop.util.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * This is class, which implements methods from ClientService
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrderService orderService;

    /**
     * This method saves client
     *
     * @param clientDTO client
     */
    @Override
    public void saveClient(ClientDTO clientDTO) {
        clientDAO.create(mapper.convertToEntity(clientDTO));
    }

    @Override
    public void updateClient(ClientDTO clientDTO) {
        clientDAO.update(mapper.convertToEntity(clientDTO));
    }

    /**
     * This method updates client
     *
     * @param clientDTO client
     */
    @Override
    public Model updateClient(String email, ClientDTO clientDTO, Model model) {

        if (!email.equals(clientDTO.getEmail())) {
            if (getClientByEmail(clientDTO.getEmail()) != null) {
                model.addAttribute("errorMessage", "Client with this email already exist. Choose another one.");
                return model;
            }
        }

        if (orderService.getUnfinishedOrdersByClient(clientDTO).size() != 0)
            model.addAttribute("errorMessage", "Data change is not possible: you have incomplete orders.");
        else {
            clientDAO.update(mapper.convertToEntity(clientDTO));

            model.addAttribute("successMessage", "Your data successfully changed");
            model.addAttribute("client", clientDTO);
        }

        return model;
    }

    /**
     * This method gets client by email
     *
     * @param email client email
     */
    @Override
    public ClientDTO getClientByEmail(String email) {
        try {
            return mapper.convertToDto(clientDAO.findByEmail(email));
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    /**
     * This method gets top 10 clients
     *
     * @return top 10 clients
     */
    @Override
    public List<ClientDTO> getTop10Clients() {
        List<Client> clients = clientDAO.findTop10Clients();
        List<ClientDTO> clientDTOS = new ArrayList<>();

        for (Client client : clients)
            clientDTOS.add(mapper.convertToDto(client));

        return clientDTOS;
    }

    /**
     * This method gets top 10 clients, who has at least 1 order
     *
     * @return list of clients
     */
    @Override
    public List<ClientDTO> getTop10ClientsWithAtLeast1Order() {
        List<Client> clients = clientDAO.findTop10ClientsWithAtLeast1Order();
        List<ClientDTO> clientDTOS = new ArrayList<>();

        for (Client client : clients)
            clientDTOS.add(mapper.convertToDto(client));

        return clientDTOS;
    }
}