package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.TrackDataMapper;
import nl.han.oose.dea.spotitube.domain.Track;
import nl.han.oose.dea.spotitube.presentation.dto.TracksDTO;

import javax.inject.Inject;


public class TrackService  {

    private TrackDataMapper dataMapper;

    @Inject
    public void setDataMapper(TrackDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public TracksDTO getAvailableTracks(int playlist) {
        return new TracksDTO(dataMapper.getAllAvailableTracks(playlist));
    }

    public TracksDTO getAllTracksFromPlaylist(int playlist) {
        return new TracksDTO(dataMapper.getAllTracksFromPlaylist(playlist));
    }

    public void removeTrackFromPlaylist(int trackId, int playlistId) {
        dataMapper.deleteTrack(trackId, playlistId);
    }

    public void addTrackToPlaylist(Track track, int playlistId) {
        dataMapper.addTrack(track, playlistId);
    }




}
