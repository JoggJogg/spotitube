package nl.han.oose.dea.spotitube.domain.services;

import java.util.List;

public interface IService<T> {

    void add(T object);

    void delete(int id);

    List findAll();

    void update(T object);
}
