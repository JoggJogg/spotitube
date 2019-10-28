package nl.han.oose.dea.spotitube.domain.services;

import nl.han.oose.dea.spotitube.domain.pojo.Track;

import java.util.List;

public interface TrackServiceInterface {

    public List<Track> getAvailableTracks(int playlist);

    public List<Track> getAllTracksFromPlaylist(int playlist);

    public void removeTrackFromPlaylist(int trackId, int playlistId);

    public void addTrackToPlaylist(Track track, int playlistId);
}

