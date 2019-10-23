package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.pojo.Playlist;
import nl.han.oose.dea.spotitube.domain.services.PlaylistService;
import nl.han.oose.dea.spotitube.presentation.dto.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;

    @Inject
    public void setItemService(PlaylistService itemService) {
        this.playlistService = itemService;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        if(token == null) return Response.status(403).build();
        return Response
                .status(200)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @Path("/{playlistId}")
    @DELETE
    @Produces("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        if(token == null) return Response.status(400).build();
        playlistService.delete(playlistId);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        playlistService.add(playlist);
        return Response
                .status(201)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("appliction/json")
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist) {
        if(token == null) return Response.status(400).build();
        playlistService.update(playlist);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }
}
