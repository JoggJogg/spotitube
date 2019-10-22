package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.LoginCredentialsDataMapper;
import nl.han.oose.dea.spotitube.domain.User;

import javax.inject.Inject;

public class LoginService {

    private AbstractMapper<User> dataMapper;

    @Inject
    public void setDataMapper(LoginCredentialsDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public boolean correctLogin(User inputUser) {
        String username = inputUser.getUser();
        User databaseUser = dataMapper.find(username);
        return inputUser.passWordsMatch(databaseUser);
    }
}
