package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.Playlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistDataMapper extends AbstractMapper {

    private static final String QUERY_FIND = "SELECT * FROM spotitube.playlists WHERE id = ?";
    private static final String QUERY_FINDALL = "SELECT * FROM spotitube.playlist";

    @Override
    protected String findStatement() { return QUERY_FIND; }

    @Override
    protected String findAllStatement() { return QUERY_FINDALL; }

    protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
        String name = rs.getString(2);
        String owner = rs.getString(3);

        return new Playlist(id, name, true);
    }
}
