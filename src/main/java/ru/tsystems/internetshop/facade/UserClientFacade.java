package ru.tsystems.internetshop.facade;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.DTO.UserDTO;
import ru.tsystems.internetshop.model.entity.Client;

public interface UserClientFacade {
    void registerUser(ClientDTO clientDTO, String password);

    void updateUser(ClientDTO clientDTO, UserDTO userDTO);
}
