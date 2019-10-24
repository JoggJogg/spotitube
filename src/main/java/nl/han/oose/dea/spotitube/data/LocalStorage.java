package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.pojo.Token;

import javax.inject.Singleton;

@Singleton
public class LocalStorage  {

    private Token token;

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
       return token;
    }
}
