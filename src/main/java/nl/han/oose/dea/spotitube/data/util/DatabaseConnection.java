package nl.han.oose.dea.spotitube.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseProperties properties;

    public DatabaseConnection() {
        this.properties = new DatabaseProperties();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(properties.connectionString());
    }
}
