package com.example.catchmrx;

import com.example.catchmrx.models.ClientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private ClientList clientlist;

    @GetMapping("/")
    public String main(HttpServletRequest request) {

        // Add information about peer to the list of clients
        String ip_address = request.getRemoteAddr();
        int port = request.getRemotePort();
        System.out.println(ip_address);
        System.out.println(port);
        clientlist.addClient(ip_address, port);

        return "index";
    }

    @GetMapping("/get_peers")
    public String get_peers(Model model) {
        model.addAttribute("clients", clientlist.getClients());
        return "peers";
    }

    @GetMapping("/find_game_room")
    public String find_game_room(Model model) {
        String room = "a";
        model.addAttribute("room", room);
        return "game_room";
    }

}
