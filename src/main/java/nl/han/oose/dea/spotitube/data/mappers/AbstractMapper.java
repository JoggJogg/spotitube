package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.data.util.DatabaseConnection;

import java.sql.*;
import java.util.*;

public abstract class AbstractMapper <T>  {

    private Connection connection;
    private PreparedStatement statement;

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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    protected List<T> loadAll(ResultSet rs) throws SQLException {
        List<T> result = new ArrayList();
        while (rs.next()) {
            result.add(load(rs));
        }
        return result;
    }

    protected T load(ResultSet rs) throws SQLException {
        T result = doLoad(rs);
        return result;
    }

    private Connection getConnection() throws SQLException {
        return new DatabaseConnection().getConnection();
    }
}
