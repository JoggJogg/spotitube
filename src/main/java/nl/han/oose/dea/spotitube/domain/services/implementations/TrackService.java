package nl.han.oose.dea.spotitube.domain.services.implementations;

import nl.han.oose.dea.spotitube.data.mappers.TrackDataMapper;
import nl.han.oose.dea.spotitube.domain.pojo.Track;
import nl.han.oose.dea.spotitube.domain.services.TrackServiceInterface;

import javax.inject.Inject;
import java.util.List;

public class TrackService implements TrackServiceInterface {

    private TrackDataMapper dataMapper;

    @Inject
    public void setDataMapper(TrackDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public List<Track> getAvailableTracks(int playlist) {
        return dataMapper.getAllAvailableTracks(playlist);
    }

    public List<Track> getAllTracksFromPlaylist(int playlist) {
        return dataMapper.getAllTracksFromPlaylist(playlist);
    }

    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        dataMapper.deleteTrack(trackId, playlistId);
    }

    public void addTrackToPlaylist(Track track, int playlistId) {
        dataMapper.addTrack(track, playlistId);
    }

}
