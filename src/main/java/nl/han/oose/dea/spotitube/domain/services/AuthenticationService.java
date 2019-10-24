package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.mappers.AbstractMapper;
import nl.han.oose.dea.spotitube.data.mappers.AuthenticationDataMapper;
import nl.han.oose.dea.spotitube.domain.exceptions.UnauthorizedUserException;
import nl.han.oose.dea.spotitube.domain.pojo.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

public class AuthenticationService {

    private AuthenticationDataMapper authenticationDataMapper;

    @Inject
    public void setDataMapper(AuthenticationDataMapper dataMapper) {
        this.authenticationDataMapper = dataMapper;
    }

    public void login(User inputUser) {
        User databaseUser = findDatabaseUser(inputUser);
        checkPassword(inputUser, databaseUser);
    }

    private User findDatabaseUser(User inputUser) {
        return authenticationDataMapper.find(inputUser);
    }

    private void checkPassword(User inputUser, User databaseUser) {
        if(!DigestUtils.sha256Hex(inputUser.getPassword()).equals(databaseUser.getPassword())) throw new UnauthorizedUserException();
    }
}
