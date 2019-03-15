package ru.tsystems.internetshop.facade.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.Role;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.RoleService;
import ru.tsystems.internetshop.service.UserService;

import java.text.ParseException;

//@Transactional ?????????
@Component
public class UserClientFacadeImpl implements UserClientFacade {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void registerUser(ClientDTO client) {
        try {
            clientService.saveClient(convertToEntity(client));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(client.getEmail(), client.getPassword());

        Role role = roleService.getRoleByName("CLIENT");

        user.getRoles().add(role);

        userService.saveUser(user);
    }

    private Client convertToEntity(ClientDTO clientDTO) throws ParseException {
        Client client = modelMapper.map(clientDTO, Client.class);
        return client;
    }
}