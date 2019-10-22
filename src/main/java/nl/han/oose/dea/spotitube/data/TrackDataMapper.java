package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackDataMapper extends AbstractMapper <Track> {

    private static final String FIND_QUERY =
            "SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, CAST(t.publicationDate AS CHAR) as publicationDate, t.description, p.offlineAvailable " +
            "FROM Track t INNER JOIN PlaylistTrack p ON t.id = p.track_id " +
            "WHERE p.playlist_id != ?";
    private static final String DELETE_QUERY = "DELETE FROM Track WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO Track (name, owner) VALUES (? , ?)";
    private static final String UPDATE_QUERY = "UPDATE Track SET name = ? WHERE id = ?";

    @Override
    protected PreparedStatement findStatement(Connection connection, int id) {
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected String deleteStatement() {
        return DELETE_QUERY;
    }

    @Override
    protected String addStatement() {
        return ADD_QUERY;
    }

    @Override
    protected String updateStatement() {
        return UPDATE_QUERY;
    }

    @Override
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

    @Override
    protected PreparedStatement setAddParameters(PreparedStatement statement, Track object) {
        return null;
    }

    @Override
    protected PreparedStatement setUpdateParameters(PreparedStatement statement, Track object) {
        return null;
    }
}
