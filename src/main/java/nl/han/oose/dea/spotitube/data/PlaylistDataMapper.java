package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDataMapper extends AbstractMapper <Playlist>  {

    private static final String QUERY_FIND = "SELECT * FROM spotitube.Playlist";

    @Override
    protected String findStatement() { return QUERY_FIND; }

    protected Playlist doLoad(int id, ResultSet rs) throws SQLException {
        String name = rs.getString(2);
        String owner = rs.getString(3);

        return new Playlist(id, name, true);
    }
}
