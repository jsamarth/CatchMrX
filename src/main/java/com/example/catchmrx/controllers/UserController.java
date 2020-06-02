package com.example.catchmrx.controllers;

import com.example.catchmrx.models.Client;
import com.example.catchmrx.models.ClientList;
import com.example.catchmrx.models.stomp.OkResponse;
import com.example.catchmrx.models.stomp.UserNameAndHostInfo;
import com.example.catchmrx.models.stomp.UserReadyToPlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    ClientList clientList;

    @MessageMapping("/userNameAndHostInfo")
    @SendTo("/findusers/userNameAndHostInfoResponse")
    public OkResponse getUser(@Header("simpSessionId") String sessionId, UserNameAndHostInfo userNameAndHostInfo) {
        clientList.addClient(userNameAndHostInfo.getName(), sessionId, Client.Status.NOT_READY, Client.GameRole.DETECTIVE);
        // System.out.println(clientList);
        System.out.println(sessionId);
        return new OkResponse(true);
    }

    @MessageMapping("/readytoplay")
    @SendTo("/findusers/readytoplayresponse")
    public OkResponse getUserReadyToPlay(@Header("simpSessionId") String sessionId, UserReadyToPlay userReadyToPlay) {
        clientList.changeClientStatus(sessionId, Client.Status.IN_LOBBY);
        System.out.println(sessionId);
        return new OkResponse(true);
    }

    // @MessageMapping("/readytoplay")
    // public OkResponse createMatchList(UserNameAndHostInfo user) {
    //     clientList.addClient(user.getName(), "localhost", 8888, Client.Status.NOT_READY, Client.GameRole.NA);
    //     return new OkResponse(new ArrayList<>(clientList.getClients().values()));
    // }
}
