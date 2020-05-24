package com.example.catchmrx;

import com.example.catchmrx.models.ClientList;
import com.example.catchmrx.models.PlayerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    private ClientList clientlist;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("player", new PlayerForm());
        return "index";
    }

    @GetMapping("/get_peers")
    public String get_peers(Model model) {
        model.addAttribute("clients", clientlist.getClients());
        return "peers";
    }

    @PostMapping("/find_game_room")
    public String find_game_room(@ModelAttribute PlayerForm player, Model model, HttpServletRequest request) {
        model.addAttribute("name", player.getName());

        // Add information about peer to the list of clients
        String ip_address = request.getRemoteAddr();
        int port = request.getRemotePort();
        System.out.println(ip_address);
        System.out.println(port);
        clientlist.addClient(player.getName(), ip_address, port);

        return "game_room";
    }

}
