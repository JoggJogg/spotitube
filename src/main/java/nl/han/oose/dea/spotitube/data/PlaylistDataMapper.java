package nl.han.oose.dea.spotitube.data;

import nl.han.oose.dea.spotitube.domain.DomainObject;
import nl.han.oose.dea.spotitube.domain.Playlist;
import nl.han.oose.dea.spotitube.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDataMapper extends AbstractMapper <Playlist> {

    public PlaylistDataMapper() throws SQLException {
    }

    @Override
    protected String findStatement() {
        return "SELECT * FROM spotitube.playlists WHERE id = ?";
    }

    @Override
    protected String findAllStatement() { return "SELECT * FROM spotitube.playlist"; }

    protected DomainObject doLoad(Long id, ResultSet rs) throws SQLException {
        String name = rs.getString(2);
        String owner = rs.getString(3);

        return new Playlist(id, name, true);
    }

}
