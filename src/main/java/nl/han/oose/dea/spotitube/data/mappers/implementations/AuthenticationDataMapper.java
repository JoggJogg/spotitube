package nl.han.oose.dea.spotitube.data.mappers.implementations;

import nl.han.oose.dea.spotitube.data.mappers.AuthenticationDataMapperInterface;
import nl.han.oose.dea.spotitube.data.connection.DatabaseConnection;
import nl.han.oose.dea.spotitube.domain.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationDataMapper implements AuthenticationDataMapperInterface {

    private Connection connection;
    private Logger logger = Logger.getLogger(getClass().getName());

    private static final String FIND_QUERY = "SELECT * FROM User WHERE username = ?";

    @Override
    public User find(User user) {
        User databaseUser = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_QUERY);
            statement.setString(1, user.getUser());
            ResultSet rs = statement.executeQuery();
            rs.next();
            databaseUser = doLoad(rs);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error executing database query: " + e);
        }
        return databaseUser;
    }

    @Override
    public User doLoad(ResultSet rs) {
        User user = null;
        try {
            String username = rs.getString(2);
            String password = rs.getString(3);
            user = new User(username, password);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving data from result set: " + e);
        }
        return user;
    }

    @Override
    public Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }
}
