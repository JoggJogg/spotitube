package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.domain.pojo.Track;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface TrackDataMapperInterface {

    List<Track> getAllTracksFromPlaylist(int playlist);

    List<Track> getAllAvailableTracks(int playlist);

    List<Track> getTracks(int playlist, String query);

    void addTrack(Track track, int playlistId);

    void deleteTrack(int trackId, int playlistId);

    List<Track> loadAll(ResultSet rs);

    Track doLoad(ResultSet rs);

    Connection getConnection();
}
