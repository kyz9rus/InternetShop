package ru.tsystems.internetshop.facade;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;

/**
 * This interface declares methods which needs to use UserService and ClientService
 */
public interface UserClientFacade {
    void registerUser(ClientDTO clientDTO, String password);

    void updateUser(ClientDTO clientDTO, UserDTO userDTO);
}
