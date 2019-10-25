package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.mappers.implementations.PlaylistDataMapper;
import nl.han.oose.dea.spotitube.domain.pojo.Playlist;
import nl.han.oose.dea.spotitube.domain.services.PlaylistServiceInterface;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements PlaylistServiceInterface {

    private PlaylistDataMapper dataMapper;

    @Inject
    public void setDataMapper(PlaylistDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public void add(Playlist playlist) {
        dataMapper.add(playlist);
    }

    @Override
    public void delete(int id) {
        dataMapper.delete(id);
    }

    @Override
    public List<Playlist> findAll() {
        return dataMapper.findAll();
    }

    @Override
    public void update(Playlist playlist) {
        dataMapper.update(playlist);
    }

    @Override
    public int getLength() {
       return dataMapper.getLength();
    }
}
