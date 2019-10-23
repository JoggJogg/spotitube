package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.pojo.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDataMapper  {

    private static final String SELECT_ALL_TRACKS_FROM_PLAYLIST =
            "SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, CAST(t.publicationDate AS CHAR) as publicationDate, t.description, p.offlineAvailable " +
            "FROM Track t INNER JOIN PlaylistTrack p ON t.id = p.track_id " +
            "WHERE p.playlist_id = ?";

    private static final String SELECT_ALL_AVAILABLE_TRACKS =
            "SELECT * FROM Track " +
            "WHERE id NOT IN (SELECT track_id FROM PlaylistTrack WHERE playlist_id = ?)";

    private static final String DELETE_TRACK_FROM_PLAYLIST =
            "DELETE FROM PlaylistTrack " +
            "WHERE playlist_id = ? AND track_id = ?";

    private static final String ADD_TRACK_TO_PLAYLIST =
            "INSERT INTO PlaylistTrack (track_id, playlist_id, offlineAvailable) " +
            "VALUES (? , ?, ?)";

    public List<Track> getAllTracksFromPlaylist(int playlist) {
        return getTracks(playlist, SELECT_ALL_TRACKS_FROM_PLAYLIST);
    }

    public List<Track> getAllAvailableTracks(int playlist) {
        return getTracks(playlist, SELECT_ALL_AVAILABLE_TRACKS);
    }

    private List<Track> getTracks(int playlist, String query) {
        List<Track> tracks = new ArrayList<>();

        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, playlist);
            ResultSet rs = statement.executeQuery();
            tracks = loadAll(rs);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tracks;
    }

    public void addTrack(Track track, int playlistId) {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(ADD_TRACK_TO_PLAYLIST);
            statement.setInt(1, track.getId());
            statement.setInt(2, playlistId);
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrack(int trackId, int playlistId) {
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(DELETE_TRACK_FROM_PLAYLIST);
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected List<Track> loadAll(ResultSet rs) throws SQLException {
        List<Track> result = new ArrayList();
        while (rs.next()) {
            result.add(doLoad(rs));
        }
        return result;
    }

    protected Track doLoad(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String performer = rs.getString("performer");
        int duration = rs.getInt("duration");
        String album = rs.getString("album");
        int playcount = rs.getInt("playcount");
        String date = rs.getString("publicationDate");
        String description = rs.getString("description");
        boolean offlineAvailable = false;
        try {
            offlineAvailable = rs.getBoolean("offlineAvailable");
        } catch (SQLException ignored) {

        }
        return new Track(id, title, performer, duration, album, playcount, date, description, offlineAvailable);
    }
}
