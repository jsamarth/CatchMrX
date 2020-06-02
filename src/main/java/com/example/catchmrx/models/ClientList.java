package com.example.catchmrx.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Getter
public class ClientList {
    private final HashMap<String, Client> clients = new HashMap<>();

    @Setter
    private int numberOfClientsInLobby;

    public ClientList() {
        this.numberOfClientsInLobby = 0;
    }

    public void addClient(String name, String sessionId, Client.Status status, Client.GameRole gameRole) {
        clients.put(sessionId, new Client(name, sessionId, status, gameRole));
        if(status == Client.Status.IN_LOBBY)
            this.numberOfClientsInLobby += 1;
    }

    public void changeClientStatus(String sessionId, Client.Status status) {
        Client client = clients.get(sessionId);
        client.setStatus(status);
        clients.put(sessionId, client);
        if(status == Client.Status.IN_LOBBY)
            this.numberOfClientsInLobby += 1;
    }

    public void changeClientGameRole(String sessionId, Client.GameRole gameRole) {
        Client client = clients.get(sessionId);
        client.setGameRole(gameRole);
        clients.put(sessionId, client);
    }

    @Override
    public String toString() {
        return clients.toString();
    }
}

