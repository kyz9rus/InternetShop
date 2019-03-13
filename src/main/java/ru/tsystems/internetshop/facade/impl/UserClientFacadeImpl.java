package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.UserService;

//@Transactional ?????????
@Component
public class UserClientFacadeImpl implements UserClientFacade {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Override
    public void registerUser(Client client) {
        clientService.saveClient(client);
        userService.saveUser(new User(client.getEmail(), client.getPassword()));

    }

}
