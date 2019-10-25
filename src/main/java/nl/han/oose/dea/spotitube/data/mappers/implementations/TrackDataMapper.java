package nl.han.oose.dea.spotitube.data.mappers.implementations;

import nl.han.oose.dea.spotitube.data.mappers.TrackDataMapperInterface;
import nl.han.oose.dea.spotitube.data.util.DatabaseConnection;
import nl.han.oose.dea.spotitube.domain.pojo.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrackDataMapper implements TrackDataMapperInterface {

    private Connection connection;
    private PreparedStatement statement;

    private Logger logger = Logger.getLogger(getClass().getName());

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

    @Override
    public List<Track> getAllTracksFromPlaylist(int playlist) {
        return getTracks(playlist, SELECT_ALL_TRACKS_FROM_PLAYLIST);
    }

    @Override
    public List<Track> getAllAvailableTracks(int playlist) {
        return getTracks(playlist, SELECT_ALL_AVAILABLE_TRACKS);
    }

    @Override
    public List<Track> getTracks(int playlist, String query) {
        List<Track> tracks = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, playlist);
            ResultSet rs = statement.executeQuery();
            tracks = loadAll(rs);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
        return tracks;
    }

    @Override
    public void addTrack(Track track, int playlistId) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(ADD_TRACK_TO_PLAYLIST);
            statement.setInt(1, track.getId());
            statement.setInt(2, playlistId);
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    @Override
    public void deleteTrack(int trackId, int playlistId) {
        try {
            connection = getConnection();
            statement = connection.prepareStatement(DELETE_TRACK_FROM_PLAYLIST);
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with the database: " + e);
        }
    }

    @Override
    public List<Track> loadAll(ResultSet rs) {
        List<Track> result = new ArrayList();
        try {
            while (rs.next()) {
                result.add(doLoad(rs));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error processing the result set: " + e);
        }
        return result;
    }

    @Override
    public Track doLoad(ResultSet rs) {
        Track track = null;
        try {
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
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Column offlineAvailable does not exist: " + e);
            }
            track = new Track(id, title, performer, duration, album, playcount, date, description, offlineAvailable);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error loading the POJO: " + e);
        }
        return track;
    }

    @Override
    public Connection getConnection() {
        return new DatabaseConnection().getConnection();
    }
}
