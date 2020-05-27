package com.example.catchmrx.controllers;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import com.example.catchmrx.models.PlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FindGameRoomController {
    @Autowired
    private ClientList clientlist;

    // This is where the players sit and press the ready button when they are ready to play
    @PostMapping("/find_game_room")
    public String find_game_room(@ModelAttribute PlayerForm player, Model model, HttpServletRequest request) {
        model.addAttribute("name", player.getName());

        // Add information about peer to the list of clients
        String ip_address = request.getRemoteAddr();
        int port = request.getRemotePort();
        System.out.println(ip_address);
        System.out.println(port);
        clientlist.addClient(player.getName(), ip_address, port, Client.Status.NOT_READY, Client.GameRole.NA);

        return "find_game_room";
    }
}
