package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.PlaylistDataMapper;
import nl.han.oose.dea.spotitube.domain.Playlist;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IService <Playlist> {

    private AbstractMapper dataMapper;

    @Inject
    public void setDataMapper(PlaylistDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public void add(Playlist playlist) {
        dataMapper.add(playlist);
    }

    public void delete(int id) {
        dataMapper.delete(id);
    }

    public List<Playlist> findAll() {
        return dataMapper.findAll(-1);
    }

    public void update(Playlist playlist) {
        dataMapper.update(playlist);
    }
}
