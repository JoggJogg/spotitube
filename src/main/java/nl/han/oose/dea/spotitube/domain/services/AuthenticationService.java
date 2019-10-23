package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.mappers.AbstractMapper;
import nl.han.oose.dea.spotitube.data.mappers.AuthenticationDataMapper;
import nl.han.oose.dea.spotitube.domain.pojo.User;

import javax.inject.Inject;
import java.util.List;

public class AuthenticationService {

    private AbstractMapper<User> dataMapper;

    @Inject
    public void setDataMapper(AuthenticationDataMapper dataMapper) {
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
