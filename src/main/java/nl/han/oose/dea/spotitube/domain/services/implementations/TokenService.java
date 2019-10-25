package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.LocalStorageInterface;
import nl.han.oose.dea.spotitube.data.implementations.LocalStorage;
import nl.han.oose.dea.spotitube.domain.exceptions.InvalidTokenException;
import nl.han.oose.dea.spotitube.domain.exceptions.MissingTokenException;
import nl.han.oose.dea.spotitube.domain.services.TokenServiceInterface;

import javax.inject.Inject;

public class TokenService implements TokenServiceInterface {

    private LocalStorageInterface localStorage;

    @Inject
    public void setLocalStorage(LocalStorageInterface localStorage) {
        this.localStorage = localStorage;
    }

    @Override
    public void validateToken(String token) {
        if(token == null) throw new MissingTokenException();
        if(localStorage.getToken() == null) throw new InvalidTokenException();
        if(!token.equals(localStorage.getToken().getToken())) throw new InvalidTokenException();
    }
}
