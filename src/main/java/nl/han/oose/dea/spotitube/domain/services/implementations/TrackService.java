package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.mappers.TrackDataMapperInterface;
import nl.han.oose.dea.spotitube.domain.pojo.Track;
import nl.han.oose.dea.spotitube.domain.services.TrackServiceInterface;

import javax.inject.Inject;
import java.util.List;

public class TrackService implements TrackServiceInterface {

    private TrackDataMapperInterface dataMapper;

    @Inject
    public void setDataMapper(TrackDataMapperInterface dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public List<Track> getAvailableTracks(int playlist) {
        return dataMapper.getAllAvailableTracks(playlist);
    }

    @Override
    public List<Track> getAllTracksFromPlaylist(int playlist) {
        return dataMapper.getAllTracksFromPlaylist(playlist);
    }

    @Override
    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        dataMapper.deleteTrack(trackId, playlistId);
    }

    @Override
    public void addTrackToPlaylist(Track track, int playlistId) {
        dataMapper.addTrack(track, playlistId);
    }
}
