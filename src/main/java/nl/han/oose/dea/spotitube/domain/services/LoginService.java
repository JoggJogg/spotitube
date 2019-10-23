package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.LoginCredentialsDataMapper;
import nl.han.oose.dea.spotitube.domain.User;

import javax.inject.Inject;
import java.util.List;

public class LoginService {

    private AbstractMapper<User> dataMapper;

    @Inject
    public void setDataMapper(LoginCredentialsDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public boolean correctLogin(User inputUser) {
        String username = inputUser.getUser();
        List<User> users = dataMapper.findAll(-1);
        User databaseUser = null;
        for(User user : users) {
            if(user.getUser().equals(username)) databaseUser = user;
        }
        return inputUser.passWordsMatch(databaseUser);
    }
}
