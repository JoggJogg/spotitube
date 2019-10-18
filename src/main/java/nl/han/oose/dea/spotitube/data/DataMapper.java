package nl.han.oose.dea.spotitube.data;

import java.util.List;

public interface DataMapper <T> {

    List<T> findAll();

    void add(T object);

    void delete(int id);

    T find(String keyword);
}
