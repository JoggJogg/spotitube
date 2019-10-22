package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.TrackDataMapper;
import nl.han.oose.dea.spotitube.presentation.TracksDTO;

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




}
