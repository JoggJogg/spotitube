package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.pojo.Token;

public interface LocalStorageInterface {

    void setToken(Token token);

    Token getToken();
}
