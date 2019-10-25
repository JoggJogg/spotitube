package nl.han.oose.dea.spotitube.data.mappers.implementations;

import nl.han.oose.dea.spotitube.data.util.DatabaseConnection;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractMapper <T> {

    private Connection connection;
    private PreparedStatement statement;

    private Logger logger = Logger.getLogger(getClass().getName());

    abstract protected PreparedStatement findStatement(Connection connection, int id);
    abstract protected PreparedStatement findAllStatement(Connection connection);
    abstract protected PreparedStatement deleteStatement(Connection connection, int id);
    abstract protected PreparedStatement addStatement(Connection connection, T object);
    abstract protected PreparedStatement updateStatement(Connection connection, T object);

    abstract protected T doLoad(ResultSet rs) throws SQLException;

    public T find(int id) {
        try {
            connection = getConnection();
            statement = findStatement(connection, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return load(rs);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
            return null;
       }
    }

    public List<T> findAll() {
        List<T> domainObjects = new ArrayList<>();

        try {
            connection = getConnection();
            statement = findAllStatement(connection);
            ResultSet results = statement.executeQuery();
            domainObjects = loadAll(results);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
        return domainObjects;
    }

    public void delete(int id) {
        try {
            connection = getConnection();
            statement = deleteStatement(connection, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    public void add(T object) {
        try {
            connection = getConnection();
            statement = addStatement(connection, object);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    public void update(T object) {
        try {
            connection = getConnection();
            statement = updateStatement(connection, object);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    private List<T> loadAll(ResultSet rs) {
        List<T> result = new ArrayList();
        try {
            while (rs.next()) {
                result.add(load(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
        return result;
    }

    private T load(ResultSet rs) {
        T result = null;
        try {
            result = doLoad(rs);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
        return result;
    }

    private Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }
}
