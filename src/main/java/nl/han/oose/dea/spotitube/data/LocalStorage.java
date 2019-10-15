package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Token;
import nl.han.oose.dea.spotitube.domain.User;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class LocalStorage {

    private List<Token> storage;

    public LocalStorage() {
        storage = new ArrayList<>();
    }

    public void add(Token token) {
        storage.add(token);
    }

    public void remove(Token token) {
        storage.remove(token);
    }

    public Token get(String username) {
        return storage.get(0);
    }

}
