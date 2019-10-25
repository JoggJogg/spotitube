package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.pojo.Playlist;
import nl.han.oose.dea.spotitube.domain.services.PlaylistServiceInterface;
import nl.han.oose.dea.spotitube.domain.services.TokenServiceInterface;
import nl.han.oose.dea.spotitube.domain.services.implementations.PlaylistService;
import nl.han.oose.dea.spotitube.domain.services.implementations.TokenService;
import nl.han.oose.dea.spotitube.presentation.dto.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    private PlaylistServiceInterface playlistService;
    private TokenServiceInterface tokenService;

    @Inject
    public void setItemService(PlaylistServiceInterface playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTokenService(TokenServiceInterface tokenService) { this.tokenService = tokenService; }

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
