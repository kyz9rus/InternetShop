package ru.tsystems.internetshop.facade;

import ru.tsystems.internetshop.model.DTO.ClientDTO;
import ru.tsystems.internetshop.model.entity.Client;

public interface UserClientFacade {
//    void registerUser(Client client);
    void registerUser(ClientDTO client);
}
