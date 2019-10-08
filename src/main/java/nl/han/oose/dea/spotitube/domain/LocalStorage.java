package nl.han.oose.dea.spotitube.domain;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage {

    private List<TokenDTO> tokens;

    public LocalStorage() {
        tokens = new ArrayList<>();
    }

    public void add(TokenDTO token) {
        tokens.add(token);
    }
}
