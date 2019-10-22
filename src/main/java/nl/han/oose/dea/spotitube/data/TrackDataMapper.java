package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDataMapper  {


    private static final String SELECT_ALL_TRACKS_FROM_PLAYLIST =
            "SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, CAST(t.publicationDate AS CHAR) as publicationDate, t.description, p.offlineAvailable " +
            "FROM Track t INNER JOIN PlaylistTrack p ON t.id = p.track_id " +
            "WHERE p.playlist_id = ?";

    private static final String SELECT_ALL_AVAILABLE_TRACKS =
            "SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, CAST(t.publicationDate AS CHAR) as publicationDate, t.description, p.offlineAvailable " +
                    "FROM Track t INNER JOIN PlaylistTrack p ON t.id = p.track_id " +
                    "WHERE p.playlist_id != ?";

    public List<Track> getAllTracksFromPlaylist(int playlist) {
        return getTracks(playlist, SELECT_ALL_TRACKS_FROM_PLAYLIST);
    }

    public List<Track> getAllAvailableTracks(int playlist) {
        return getTracks(playlist, SELECT_ALL_AVAILABLE_TRACKS);
    }

    private List<Track> getTracks(int playlist, String selectAllAvailableTracks) {
        List<Track> tracks = new ArrayList<>();

        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(selectAllAvailableTracks);
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
        boolean offlineAvailable = rs.getBoolean("offlineAvailable");
        return new Track(id, title, performer, duration, album, playcount, date, description, offlineAvailable);
    }

}
