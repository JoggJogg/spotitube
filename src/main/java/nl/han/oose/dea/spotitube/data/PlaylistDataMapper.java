package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDataMapper extends AbstractMapper <Playlist>  {

    private static final String FIND_QUERY = "SELECT * FROM Playlist";
    private static final String DELETE_QUERY = "DELETE FROM Playlist WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO Playlist (name, owner) VALUES (? , ?)";
    private static final String UPDATE_QUERY = "UPDATE Playlist SET name = ? WHERE id = ?";

    @Override
    protected PreparedStatement findStatement(Connection connection, int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected String deleteStatement() { return DELETE_QUERY; }

    @Override
    protected String addStatement() { return ADD_QUERY; }

    @Override
    protected String updateStatement() {
        return UPDATE_QUERY;
    }

    @Override
    protected PreparedStatement setAddParameters(PreparedStatement statement, Playlist object) {
        try {
            LocalStorage localStorage = LocalStorage.getInstance();
            statement.setString(1, object.getName());
            statement.setString(2, localStorage.getToken().getUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected PreparedStatement setUpdateParameters(PreparedStatement statement, Playlist object) {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    protected Playlist doLoad(ResultSet rs) throws SQLException {
        LocalStorage localStorage = LocalStorage.getInstance();
        int id = rs.getInt(1);
        String name = rs.getString(2);
        String owner = rs.getString(3);
        String currentUser = localStorage.getToken().getUser();
        return new Playlist(id, name, owner.equals(currentUser));
    }


}
