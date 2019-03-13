package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Role;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.RoleService;
import ru.tsystems.internetshop.service.UserService;

//@Transactional ?????????
@Component
public class UserClientFacadeImpl implements UserClientFacade {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void registerUser(Client client) {
        clientService.saveClient(client);

        User user = new User(client.getEmail(), client.getPassword());

        Role role = roleService.getRoleByName("CLIENT");

        user.getRoles().add(role);

        userService.saveUser(user);
    }
}