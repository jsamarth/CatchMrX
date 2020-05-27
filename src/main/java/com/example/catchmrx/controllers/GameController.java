package com.example.catchmrx.controllers;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {
    @Autowired
    private ClientList clientlist;

    @GetMapping("/game")
    public String enterGame(Model model) {
        // get first 6 clients
        List<Client> sixClients = getSixClients(this.clientlist);

        model.addAttribute("clients", sixClients);
        return "game";
    }

    public List<Client> getSixClients(ClientList clientlist) {
        List<Client> gameClientList = new ArrayList<>();
        int i = 0;
        for(Map.Entry<String, Client> entry: clientlist.getClients().entrySet()) {
            if(i == 6)
                break;

            Client client = entry.getValue();
            if(client.getStatus() != Client.Status.IN_LOBBY)
                continue;

            client.setStatus(Client.Status.IN_GAME);
            clientlist.changeClientStatus(entry.getKey(), Client.Status.IN_GAME);
            if(i == 0)
                client.setGameRole(Client.GameRole.MR_X);
            else
                client.setGameRole(Client.GameRole.DETECTIVE);

            i++;
            gameClientList.add(client);
        }
        return gameClientList;
    }
}
