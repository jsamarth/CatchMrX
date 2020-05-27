package com.example.catchmrx.models;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
public class ClientList {
    private HashMap<String, Client> clients = new HashMap<>();

    public void addClient(String name, String ip_address, int port, Client.Status status, Client.GameRole gameRole) {
        clients.put(name, new Client(name, ip_address, port, status, gameRole));
    }

    public void changeClientStatus(String name, Client.Status status) {
        Client client = clients.get(name);
        client.setStatus(status);
        clients.put(name, client);
    }

    public void changeClientGameRole(String name, Client.GameRole gameRole) {
        Client client = clients.get(name);
        client.setGameRole(gameRole);
        clients.put(name, client);
    }
}

