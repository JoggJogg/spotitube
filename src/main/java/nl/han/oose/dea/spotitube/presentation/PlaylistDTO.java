package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.Playlist;

import java.util.List;

public class PlaylistDTO {

    private List<Playlist> playlists;
    private int seconds;

    public PlaylistDTO(List playlists, int seconds) {
        this.playlists = playlists;
        this.seconds = seconds;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getSeconds() {
        return seconds;
    }
}
