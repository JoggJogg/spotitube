package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.pojo.Playlist;
import nl.han.oose.dea.spotitube.domain.services.PlaylistService;
import nl.han.oose.dea.spotitube.domain.services.TokenService;
import nl.han.oose.dea.spotitube.presentation.dto.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistService playlistService;
    private TokenService tokenService;

    @Inject
    public void setItemService(PlaylistService itemService) {
        this.playlistService = itemService;
    }

    @Inject
    public void setTokenService(TokenService tokenService) { this.tokenService = tokenService; }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        tokenService.validateToken(token);
        return Response
                .status(200)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @Path("/{playlistId}")
    @DELETE
    @Produces("application/json")
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        tokenService.validateToken(token);
        playlistService.delete(playlistId);
        return Response
                .status(Response.Status.OK)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(@QueryParam("token") String token, Playlist playlist) {
        tokenService.validateToken(token);
        playlistService.add(playlist);
        return Response
                .status(Response.Status.CREATED)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response editPlaylist(@QueryParam("token") String token, Playlist playlist) {
        tokenService.validateToken(token);
        playlistService.update(playlist);
        return Response
                .status(Response.Status.OK)
                .entity(new PlaylistsDTO(playlistService.findAll(), playlistService.getLength()))
                .build();
    }
}
