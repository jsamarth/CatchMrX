package com.example.catchmrx.models.stomp;

import com.example.catchmrx.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchedGroupOfSixResponse {
    private List<Client> matchedClientList;
}
