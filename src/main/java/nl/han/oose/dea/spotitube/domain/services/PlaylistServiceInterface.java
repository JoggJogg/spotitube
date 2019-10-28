package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.domain.pojo.Playlist;

import java.util.List;

public interface PlaylistServiceInterface {

    void add(Playlist playlist);

    void delete(int id);

    List<Playlist> findAll();

    void update(Playlist playlist);

    int getLength();
}
