package com.example.catchmrx.configurations;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatchMakerTest {

    private ClientList clientListLessThan6;
    private ClientList clientListMoreThan6;
    private MatchMaker matchMaker;

    @BeforeEach
    void setUp() {
        clientListLessThan6 = new ClientList();
        clientListLessThan6.addClient("A", "a", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("B", "b", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("C", "c", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("D", "d", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("E", "e", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("F", "f", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("G", "g", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("H", "h", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListLessThan6.addClient("I", "i", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);

        clientListMoreThan6 = new ClientList();
        clientListMoreThan6.addClient("A", "a", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("B", "b", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("C", "c", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("D", "d", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("E", "e", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("F", "f", Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("G", "g", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("H", "h", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);
        clientListMoreThan6.addClient("I", "i", Client.Status.IN_LOBBY, Client.GameRole.DETECTIVE);

        matchMaker = new MatchMaker();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createMatchListWorksForLessThan6Players() {
        List<Client> expected = Collections.emptyList();
        List<Client> actual = matchMaker.createMatchList(clientListLessThan6);
        assertEquals(expected.size(), actual.size());
    }

    @Test
    void createMatchListWorksForMoreThan6Players() {
        List<Client> actual = matchMaker.createMatchList(clientListMoreThan6);
        assertEquals(6, actual.size());
        assertTrue(actual.get(0).getName().equals("A") && actual.get(0).getStatus() == Client.Status.IN_GAME
                    && actual.get(0).getGameRole() == Client.GameRole.MR_X);
        assertTrue(actual.get(1).getName().equals("B") && actual.get(1).getStatus() == Client.Status.IN_GAME);
        assertTrue(actual.get(2).getName().equals("C") && actual.get(2).getStatus() == Client.Status.IN_GAME);
        assertTrue(actual.get(3).getName().equals("E") && actual.get(3).getStatus() == Client.Status.IN_GAME);
        assertTrue(actual.get(4).getName().equals("G") && actual.get(4).getStatus() == Client.Status.IN_GAME);
        assertTrue(actual.get(5).getName().equals("H") && actual.get(5).getStatus() == Client.Status.IN_GAME);
    }
}