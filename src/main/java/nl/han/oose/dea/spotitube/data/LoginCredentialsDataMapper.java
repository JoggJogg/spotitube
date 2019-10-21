package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCredentialsDataMapper extends AbstractMapper <User> {

    private static final String FIND_QUERY = "SELECT * FROM spotitube.User WHERE username = ?";
    private static final String DELETE_QUERY = "DELETE FROM spotitube.User WHERE username = ?";
    private static final String ADD_QUERY = "INSERT INTO spotitube.User (username, password) VALUES (?, ?)";

    @Override
    protected String findStatement() {
        return FIND_QUERY;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_QUERY;
    }

    @Override
    protected String addStatement() { return ADD_QUERY; }

    @Override
    protected String updateStatement() {
        return null;
    }

    @Override
    protected User doLoad(ResultSet rs) throws SQLException {
        String username = rs.getString(2);
        String password = rs.getString(3);
        return new User(username, password);
    }

    // TODO implement
    @Override
    protected PreparedStatement setAddParameters(PreparedStatement statement, User object) {
        return null;
    }

    @Override
    protected PreparedStatement setUpdateParameters(PreparedStatement statement, User object) {
        return null;
    }

    public boolean correctLogin(User inputUser) throws SQLException {
        String username = inputUser.getUser();
        User databaseUser = find(username);
        return inputUser.passWordsMatch(databaseUser);
    }
}
