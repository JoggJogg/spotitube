package nl.han.oose.dea.spotitube.presentation.dto;

import nl.han.oose.dea.spotitube.domain.pojo.Track;

import java.util.List;

public class TracksDTO {

    private List<Track> tracks;

    public TracksDTO() {}

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
