package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.DomainObject;

import java.sql.*;
import java.util.*;

public abstract class AbstractMapper <T extends  DomainObject>  {

    protected Map loadedMap = new HashMap();

    abstract protected String findStatement();
    abstract protected T doLoad(int id, ResultSet rs) throws SQLException;


    protected T find(String keyword) throws SQLException {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());

            PreparedStatement findStatement = connection.prepareStatement(findStatement());
            findStatement.setString(1, keyword);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            T result = load(rs);
            return result;
        } catch (SQLException e) {
            return null;
       }
    }

    public List<T> findAll() {
        List<T> domainObjects = new ArrayList<>();

        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(findStatement());
            ResultSet results = statement.executeQuery();
            domainObjects = loadAll(results);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return domainObjects;
    }

    protected List<T> loadAll(ResultSet rs) throws SQLException {
        List<T> result = new ArrayList();
        while (rs.next()) {
            result.add(load(rs));
        }
        return result;
    }

    protected T load(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        T result = doLoad(id, rs);
        return result;
    }


}
