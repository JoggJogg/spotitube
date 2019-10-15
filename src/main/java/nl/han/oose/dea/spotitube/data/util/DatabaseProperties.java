package nl.han.oose.dea.spotitube.data.util;

import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {

    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            Class.forName(properties.getProperty("driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String connectionString() {
        return properties.getProperty("connectionString") + "&serverTimezone=UTC";
    }



}
