package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.domain.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDataMapper extends AbstractMapper <User> {

    private static final String FIND_QUERY = "SELECT * FROM User";
    private static final String DELETE_QUERY = "DELETE FROM User WHERE username = ?";
    private static final String ADD_QUERY = "INSERT INTO User (username, password) VALUES (?, ?)";

    @Override
    protected PreparedStatement findStatement(Connection connection, int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
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
}
