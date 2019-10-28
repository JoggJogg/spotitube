package nl.han.oose.dea.spotitube.data.connection;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {

    private Properties properties;

    private Logger logger = Logger.getLogger(getClass().getName());

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    public String connectionString() {
        return properties.getProperty("connectionString") + "&serverTimezone=UTC";
    }
}
