package com.example.catchmrx.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Data
public class ClientList {
    private ArrayList<Client> clients = new ArrayList<>();

    public void addClient(String name, String ip_address, int port) {
        this.clients.add(new Client(name, ip_address, port));
    }
}

@Data
@AllArgsConstructor
class Client {
    private String name;
    private String ip_address;
    private int port;
}
