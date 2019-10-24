package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.exceptions.BadRequestException;
import nl.han.oose.dea.spotitube.domain.exceptions.ForbiddenRequestException;

import javax.inject.Inject;

public class TokenService {

    private LocalStorage localStorage;

    @Inject
    public void setLocalStorage(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    public void validateToken(String token) {
        if(token == null || localStorage.getToken() == null) throw new BadRequestException();
        if(!token.equals(localStorage.getToken().getToken())) throw new ForbiddenRequestException();
    }
}
