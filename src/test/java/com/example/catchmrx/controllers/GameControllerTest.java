package com.example.catchmrx.controllers;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {

    ClientList clientlist;

    @BeforeEach
    void setUp() {
        clientlist = new ClientList();
        clientlist.addClient("A", "192", 50, Client.Status.NOT_READY, Client.GameRole.NA);
        clientlist.addClient("B", "200", 60, Client.Status.IN_LOBBY, Client.GameRole.NA);
        clientlist.addClient("C", "300", 70, Client.Status.IN_LOBBY, Client.GameRole.NA);
        clientlist.addClient("D", "120", 80, Client.Status.NOT_READY, Client.GameRole.NA);
        clientlist.addClient("E", "140", 90, Client.Status.NOT_READY, Client.GameRole.NA);
        clientlist.addClient("F", "150", 40, Client.Status.NOT_READY, Client.GameRole.NA);
        clientlist.addClient("G", "210", 30, Client.Status.IN_LOBBY, Client.GameRole.NA);
        clientlist.addClient("H", "901", 10, Client.Status.IN_LOBBY, Client.GameRole.NA);
        clientlist.addClient("I", "800", 500, Client.Status.IN_LOBBY, Client.GameRole.NA);
        clientlist.addClient("J", "700", 502, Client.Status.IN_LOBBY, Client.GameRole.NA);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSixClients() {
        GameController gameController = new GameController();
        List<Client> testList = gameController.getSixClients(clientlist);

        assertTrue(testList.size() == 6);
        for(int i = 0; i < 6; i++) {
            assertTrue(testList.get(i).getStatus() == Client.Status.IN_GAME);
            assertTrue(testList.get(i).getGameRole() == Client.GameRole.DETECTIVE ||
                    testList.get(i).getGameRole() == Client.GameRole.MR_X);
        }

    }
}