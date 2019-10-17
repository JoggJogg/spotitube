package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Token;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage implements DataMapper <Token> {

    private List<Token> tokens;

    public LocalStorage() {
        tokens = new ArrayList<>();
    }

    @Override
    public List<Token> findAll() {
       return tokens;
    }

    @Override
    public void add(Token object) {
        tokens.add(object);
    }

    @Override
    public void delete(int id) {
        tokens.remove(id);
    }

    @Override
    public Token find(String keyword) {
        for(Token token: tokens) {
            if(token.getUser().equals(keyword)) {
                return token;
            }
        }
        return null;
    }
}
