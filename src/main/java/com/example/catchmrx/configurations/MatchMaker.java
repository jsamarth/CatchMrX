package com.example.catchmrx.configurations;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import com.example.catchmrx.models.stomp.MatchedGroupOfSixResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class MatchMaker {

    @Autowired
    private ClientList globalClientList;

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 10000)
    private void sendMatchList() {
        List<Client> matchClientList = createMatchList(globalClientList);
        for(Client client: matchClientList) {
            template.convertAndSendToUser(client.getSessionId(), "/queue/matchplayers",
                    new MatchedGroupOfSixResponse(matchClientList), createHeaders(client.getSessionId()));
            // template.convertAndSend("/match/matchplayers", new MatchedGroupOfSixResponse(matchClientList));
            System.out.println("Sent a matchup! ============");
        }

        System.out.println("\nPlayers in a game\n---------------------------");
        for(Client client: matchClientList)
            System.out.println(client.getName());
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

    public List<Client> createMatchList(ClientList clientList) {

        List<Client> matchList = new ArrayList<>();
        List<String> allClients = new ArrayList<>(clientList.getClients().keySet());

        if(allClients.size() < 6 || clientList.getNumberOfClientsInLobby() < 6) {
            return matchList;
        }

        int count = 0;
        for(String sessionId: allClients) {
            if (count >= 6)
                break;

            Client client = clientList.getClients().get(sessionId);
            if(client.getStatus() == Client.Status.IN_LOBBY) {
                count += 1;
                client.setStatus(Client.Status.IN_GAME);
                clientList.changeClientStatus(sessionId, Client.Status.IN_GAME);
                if(count == 1) {
                    client.setGameRole(Client.GameRole.MR_X);
                    clientList.changeClientGameRole(sessionId, Client.GameRole.MR_X);
                }
                matchList.add(client);
            }
        }

        clientList.setNumberOfClientsInLobby(clientList.getNumberOfClientsInLobby() - 6);
        return matchList;
    }
}
