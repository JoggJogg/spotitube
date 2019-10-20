package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.PlaylistDataMapper;
import nl.han.oose.dea.spotitube.domain.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistService {

    private AbstractMapper dataMapper;
    private static final int MOCK_DURATION = 1234;

    @Inject
    public void setDataMapper(PlaylistDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if(token == null) return Response.status(400).build();
        return Response
                .status(200)
                .entity(new PlaylistsDTO(dataMapper.findAll(), MOCK_DURATION))
                .build();
    }

    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        if(token == null) return Response.status(400).build();
        dataMapper.delete(id);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(dataMapper.findAll(), MOCK_DURATION))
                .build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        dataMapper.add(playlist);
        return Response
                .status(201)
                .entity(new PlaylistsDTO(dataMapper.findAll(), MOCK_DURATION))
                .build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("appliction/json")
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        dataMapper.update(playlist);
        return null;
    }
}
