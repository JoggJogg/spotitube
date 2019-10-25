package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.mappers.implementations.AuthenticationDataMapper;
import nl.han.oose.dea.spotitube.domain.exceptions.UnauthorizedUserException;
import nl.han.oose.dea.spotitube.domain.pojo.User;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationServiceInterface;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;

public class AuthenticationService implements AuthenticationServiceInterface {

    private AuthenticationDataMapper authenticationDataMapper;

    @Inject
    public void setDataMapper(AuthenticationDataMapper dataMapper) {
        this.authenticationDataMapper = dataMapper;
    }

    @Override
    public void login(User inputUser) {
        User databaseUser = findDatabaseUser(inputUser);
        checkPassword(inputUser, databaseUser);
    }

    @Override
    public User findDatabaseUser(User inputUser) {
        return authenticationDataMapper.find(inputUser);
    }

    @Override
    public void checkPassword(User inputUser, User databaseUser) {
        if(!DigestUtils.sha256Hex(inputUser.getPassword()).equals(databaseUser.getPassword())) throw new UnauthorizedUserException();
    }
}
