package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.domain.pojo.User;

public interface AuthenticationServiceInterface {

   void login(User inputUser);

   User findDatabaseUser(User inputUser);

   void checkPassword(User inputUser, User databaseUser);
}
