package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDataMapper extends AbstractMapper <Playlist>  {

    private static final String FIND_QUERY = "SELECT * FROM spotitube.Playlist";
    private static final String DELETE_QUERY = "DELETE FROM spotitube.Playlist WHERE id = ?";

    @Override
    protected String findStatement() { return FIND_QUERY; }

    @Override
    protected String deleteStatement() { return DELETE_QUERY; }

    protected Playlist doLoad(int id, ResultSet rs) throws SQLException {
        LocalStorage localStorage = LocalStorage.getInstance();
        String name = rs.getString(2);
        String owner = rs.getString(3);
        // TODO change the method find so that it doesnt need input parameter for LocalStorage
        String currentUser = localStorage.find("CHANGE-THIS").getUser();
        return new Playlist(id, name, owner.equals(currentUser));
    }
}
