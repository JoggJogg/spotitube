package nl.han.oose.dea.spotitube.presentation.dto;

import nl.han.oose.dea.spotitube.domain.Playlist;

import java.util.List;

public class PlaylistsDTO {

    private List<Playlist> playlists;
    private int duration;

    public PlaylistsDTO(List playlists, int duration) {
        this.playlists = playlists;
        this.duration = duration;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getDuration() {
        return duration;
    }
}
