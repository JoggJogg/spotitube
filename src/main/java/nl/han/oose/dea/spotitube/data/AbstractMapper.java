package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.DomainObject;

import java.sql.*;
import java.util.*;

public abstract class AbstractMapper<T>  {

    protected Map loadedMap = new HashMap();

    protected AbstractMapper() throws SQLException {
    }

    abstract protected String findStatement();
    abstract protected String findAllStatement();
    abstract protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException;


    protected DomainObject abstractFind(Long id) throws SQLException {
//        Connection connection = null;
//        try {
////            connection = DriverManager.getConnection(properties.connectionString());
////            DomainObject result = (DomainObject) loadedMap.get(id); if (result != null) return result;
////            PreparedStatement findStatement = null;
////            findStatement = connection.prepareStatement(findStatement());
////            findStatement.setLong(1, id.longValue());
////            ResultSet rs = findStatement.executeQuery();
////            rs.next();
////            result = load(rs);
////            return result;
//        } catch (SQLException e) {
            return null;
//        }
    }

    public List<DomainObject> findAll() {
        List<DomainObject> domainObjects = new ArrayList<>();

        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(findAllStatement());
            ResultSet results = statement.executeQuery();
            domainObjects = loadAll(results);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return domainObjects;
    }

    protected List<DomainObject> loadAll(ResultSet rs) throws SQLException {
        List<DomainObject> result = new ArrayList();
        while (rs.next()) {
            result.add(load(rs));
        }
        return result;
    }

    protected DomainObject load(ResultSet rs) throws SQLException {
        Long id = rs.getLong(1);
        if (loadedMap.containsKey(id)) return (DomainObject) loadedMap.get(id);
        DomainObject result = doLoad(id, rs);
        loadedMap.put(id, result);
        return result;
    }


}
