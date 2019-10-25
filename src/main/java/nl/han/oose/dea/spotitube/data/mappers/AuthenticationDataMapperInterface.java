package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.domain.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;

public interface AuthenticationDataMapperInterface {

    User find(User user);

    User doLoad(ResultSet rs);

    Connection getConnection();
}
