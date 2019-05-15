package ru.tsystems.internetshop.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.internetshop.facade.UserClientFacade;
import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.Role;
import ru.tsystems.internetshop.model.entity.User;
import ru.tsystems.internetshop.service.ClientService;
import ru.tsystems.internetshop.service.RoleService;
import ru.tsystems.internetshop.service.UserService;
import ru.tsystems.internetshop.util.Mapper;

/**
 * This class override methods from UserClientFacade interface
 */
@Transactional
@Component
public class UserClientFacadeImpl implements UserClientFacade {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Mapper mapper;

    /**
     * This method create an user, create a role and binds its
     *
     * @param clientDTO client
     * @param password  password
     */
    @Override
    public void registerUser(ClientDTO clientDTO, String password) {
        password = (new BCryptPasswordEncoder().encode(password));
        clientService.saveClient(clientDTO);

        User user = new User(clientDTO.getEmail(), password);

        Role role = roleService.getRoleByName("CLIENT");

        user.getRoles().add(role);

        userService.saveUser(user);
    }

    /**
     * This method update client entity and user entity in database
     *
     * @param clientDTO client
     * @param userDTO   user
     */
    @Override
    public void updateUser(ClientDTO clientDTO, UserDTO userDTO) {
        clientService.updateClient(clientDTO);

        userService.updateUser(mapper.convertToEntity(userDTO));
    }
}