package nl.han.oose.dea.spotitube.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DataMapper <T> {

    List<T> findAll(ResultSet rs) throws SQLException;

    void add(T object);

    void delete(int id);

    T find(String keyword);
}
