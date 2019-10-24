package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.data.util.DatabaseConnection;
import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDataMapper  {

    private Connection connection;

    private static final String FIND_QUERY = "SELECT * FROM User WHERE username = ?";

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
            e.printStackTrace();
        }
        return databaseUser;
    }

    protected User doLoad(ResultSet rs) throws SQLException {
        String username = rs.getString(2);
        String password = rs.getString(3);
        return new User(username, password);
    }

    private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }


}
