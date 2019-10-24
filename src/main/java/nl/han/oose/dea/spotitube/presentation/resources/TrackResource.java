package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.pojo.Token;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationService;
import nl.han.oose.dea.spotitube.domain.services.TokenService;
import nl.han.oose.dea.spotitube.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private TrackService trackService;
    private TokenService tokenService;

    @Inject
    public void setTrackService(TrackService trackService) { this.trackService = trackService; }

    @Inject
    public void setTokenService(TokenService tokenService) { this.tokenService = tokenService; }

    @GET
    @Produces("application/json")
    public Response getAvailableTracks(@QueryParam("forPlaylist") int playlist, @QueryParam("token") String token) {
        tokenService.validateToken(token);
        return Response
                .status(200)
                .entity(trackService.getAvailableTracks(playlist))
                .build();
    }

}
