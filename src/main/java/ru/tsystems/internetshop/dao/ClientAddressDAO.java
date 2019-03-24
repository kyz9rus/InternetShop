package ru.tsystems.internetshop.dao;

import ru.tsystems.internetshop.model.entity.Client;
import ru.tsystems.internetshop.model.entity.ClientAddress;

import java.util.List;

public interface ClientAddressDAO extends DAO<ClientAddress, Long> {
    List<ClientAddress> getAddressesByClient(Client client);
}