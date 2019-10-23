package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.pojo.DomainObject;

import java.sql.*;
import java.util.*;

public abstract class AbstractMapper <T extends  DomainObject>  {

    abstract protected PreparedStatement findStatement(Connection connection, int id);
    abstract protected String deleteStatement();
    abstract protected String addStatement();
    abstract protected String updateStatement();

    abstract protected T doLoad(ResultSet rs) throws SQLException;
    abstract protected PreparedStatement setAddParameters(PreparedStatement statement, T object);
    abstract protected PreparedStatement setUpdateParameters(PreparedStatement statement, T object);

    public T find(int id) {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement findStatement = findStatement(connection, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            T result = load(rs);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
       }
    }

    public List<T> findAll(int id) {
        List<T> domainObjects = new ArrayList<>();

        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = findStatement(connection, id);
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
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(deleteStatement());
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(T object) {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(addStatement());
            statement = setAddParameters(statement, object);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(T object) {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(updateStatement());
            statement = setUpdateParameters(statement, object);
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
}