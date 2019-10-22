package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Token;

public class LocalStorage  {

    private Token token;

    private static LocalStorage soleInstance = new LocalStorage();

    public static LocalStorage getInstance() {
        return soleInstance;
    }

    private LocalStorage() {}

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
       return token;
    }
}
