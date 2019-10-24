package nl.han.oose.dea.spotitube.data.mappers;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.data.util.DatabaseProperties;
import nl.han.oose.dea.spotitube.domain.pojo.Playlist;

import javax.inject.Inject;
import java.sql.*;

public class PlaylistDataMapper extends AbstractMapper <Playlist>  {

    private static final String FIND_QUERY = "SELECT * FROM Playlist WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM Playlist";
    private static final String DELETE_QUERY = "DELETE FROM Playlist WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO Playlist (name, owner) VALUES (? , ?)";
    private static final String UPDATE_QUERY = "UPDATE Playlist SET name = ? WHERE id = ?";
    private static final String LENGTH_QUERY = "SELECT SUM(duration) as length from Track INNER JOIN PlayListTrack PLT on Track.id = PLT.track_id";

    private LocalStorage localStorage;

    @Inject
    public void setLocalStorage(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    @Override
    protected PreparedStatement findStatement(Connection connection, int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_QUERY);
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement findAllStatement(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement= connection.prepareStatement(FIND_ALL_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement deleteStatement(Connection connection, int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement addStatement(Connection connection, Playlist object) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_QUERY);
            statement.setString(1, object.getName());
            statement.setString(2, localStorage.getToken().getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement updateStatement(Connection connection, Playlist object) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected Playlist doLoad(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String owner = rs.getString(3);
        String currentUser = localStorage.getToken().getUser();
        return new Playlist(id, name, owner.equals(currentUser));
    }

    public int getLength() {
        int length = 0;
        try {
            DatabaseProperties properties = new DatabaseProperties();
            Connection connection = DriverManager.getConnection(properties.connectionString());
            PreparedStatement statement = connection.prepareStatement(LENGTH_QUERY);
            ResultSet rs = statement.executeQuery();
            rs.next();
            length = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return length;
    }
}
