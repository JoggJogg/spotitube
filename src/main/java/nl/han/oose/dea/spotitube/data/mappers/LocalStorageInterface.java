package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.domain.pojo.Token;

public interface LocalStorageInterface {

    void setToken(Token token);

    Token getToken();
}
