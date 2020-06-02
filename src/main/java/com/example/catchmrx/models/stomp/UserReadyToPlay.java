package com.example.catchmrx.models.stomp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadyToPlay {
    private boolean isReadyToPlay;
}
