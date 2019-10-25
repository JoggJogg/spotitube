package nl.han.oose.dea.spotitube.data.implementations;

import nl.han.oose.dea.spotitube.data.LocalStorageInterface;
import nl.han.oose.dea.spotitube.domain.pojo.Token;

import javax.inject.Singleton;

@Singleton
public class LocalStorage implements LocalStorageInterface {

    private Token token;

    @Override
    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public Token getToken() {
       return token;
    }
}
