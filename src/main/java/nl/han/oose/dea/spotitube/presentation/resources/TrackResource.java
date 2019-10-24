package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.services.TokenService;
import nl.han.oose.dea.spotitube.domain.services.TrackService;
import nl.han.oose.dea.spotitube.presentation.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
                .status(Response.Status.OK)
                .entity(new TracksDTO(trackService.getAvailableTracks(playlist)))
                .build();
    }
}
