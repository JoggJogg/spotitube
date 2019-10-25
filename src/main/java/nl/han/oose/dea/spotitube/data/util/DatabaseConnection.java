package nl.han.oose.dea.spotitube.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private DatabaseProperties properties;

    private Logger logger = Logger.getLogger(getClass().getName());

    public DatabaseConnection() {
        this.properties = new DatabaseProperties();
    }

    public Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(properties.connectionString());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error establishing connection with the database: " + e);

        }
        return connection;
    }
}
