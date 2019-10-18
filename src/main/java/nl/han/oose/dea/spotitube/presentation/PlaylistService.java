package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.PlaylistDataMapper;
import nl.han.oose.dea.spotitube.domain.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;

@Path("/playlists")
public class PlaylistService {

    private AbstractMapper dataMapper;

    @Inject
    public void setDataMapper(PlaylistDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        return Response
                .status(200)
                .entity(new PlaylistsDTO(dataMapper.findAll(), 1234))
                .build();
    }

    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        dataMapper.delete(id);
        return Response
                .status(200)
                .entity(dataMapper.findAll())
                .build();
    }
}
