package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.TrackDataMapper;
import nl.han.oose.dea.spotitube.domain.Track;
import nl.han.oose.dea.spotitube.presentation.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService  {

    private AbstractMapper dataMapper;

    @Inject
    public void setDataMapper(TrackDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public TracksDTO getAvailableTracks(int playlist) {
        return new TracksDTO(dataMapper.findAll(playlist));
    }




}
