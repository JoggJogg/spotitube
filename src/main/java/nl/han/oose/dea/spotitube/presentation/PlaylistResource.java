package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.Playlist;
import nl.han.oose.dea.spotitube.domain.services.IService;
import nl.han.oose.dea.spotitube.domain.services.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private IService itemService;

    private static final int MOCK_DURATION = 1234;

    @Inject
    public void setItemService(PlaylistService itemService) {
        this.itemService = itemService;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if(token == null) return Response.status(400).build();
        return Response
                .status(200)
                .entity(new PlaylistsDTO(itemService.findAll(), MOCK_DURATION))
                .build();
    }

    @Path("/{id}")
    @DELETE
    @Produces("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        if(token == null) return Response.status(400).build();
        itemService.delete(id);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(itemService.findAll(), MOCK_DURATION))
                .build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        itemService.add(playlist);
        return Response
                .status(201)
                .entity(new PlaylistsDTO(itemService.findAll(), MOCK_DURATION))
                .build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("appliction/json")
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        itemService.update(playlist);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(itemService.findAll(), MOCK_DURATION))
                .build();
    }
}
