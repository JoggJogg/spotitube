package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.exceptions.InvalidTokenException;
import nl.han.oose.dea.spotitube.domain.exceptions.MissingTokenException;
import nl.han.oose.dea.spotitube.domain.exceptions.UnauthorizedUserException;
import nl.han.oose.dea.spotitube.domain.services.TokenServiceInterface;

import javax.inject.Inject;

public class TokenService implements TokenServiceInterface {

    private LocalStorage localStorage;

    @Inject
    public void setLocalStorage(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    public void validateToken(String token) {
        if(token == null) throw new MissingTokenException();
        if(localStorage.getToken() == null) throw new InvalidTokenException();
        if(!token.equals(localStorage.getToken().getToken())) throw new InvalidTokenException();
    }
}
