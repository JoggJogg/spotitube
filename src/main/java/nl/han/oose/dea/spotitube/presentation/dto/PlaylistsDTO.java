package nl.han.oose.dea.spotitube.presentation.dto;

import nl.han.oose.dea.spotitube.domain.pojo.Playlist;

import java.util.List;

public class PlaylistsDTO {

    private List<Playlist> playlists;
    private int length;

    public PlaylistsDTO() {}

    public PlaylistsDTO(List playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

