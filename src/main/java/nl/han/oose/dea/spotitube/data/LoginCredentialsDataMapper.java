package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCredentialsDataMapper extends AbstractMapper <User> {

    private static final String FIND_QUERY = "SELECT * FROM spotitube.User WHERE username = ?";
    private static final String DELETE_QUERY = "DELETE FROM spotitube.User WHERE username = ?";

    @Override
    protected String findStatement() {
        return FIND_QUERY;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_QUERY;
    }

    @Override
    protected User doLoad(int id, ResultSet rs) throws SQLException {
        String username = rs.getString(2);
        String password = rs.getString(3);
        return new User(username, password);
    }

    public boolean correctLogin(User inputUser) throws SQLException {
        String username = inputUser.getUser();
        User databaseUser = find(username);
        return inputUser.passWordsMatch(databaseUser);
    }
}
