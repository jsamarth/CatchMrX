package com.example.catchmrx.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private String name;
    private String sessionId;
    private Status status;
    private GameRole gameRole;

    public enum Status {NOT_READY, IN_LOBBY, IN_GAME, END_OF_GAME}
    public enum GameRole {DETECTIVE, MR_X}
}
