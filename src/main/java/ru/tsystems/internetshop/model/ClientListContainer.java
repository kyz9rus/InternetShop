package ru.tsystems.internetshop.model;

import java.util.List;

public class ClientListContainer {
    private List<ClientDto> clients;

    public List<ClientDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }
}
