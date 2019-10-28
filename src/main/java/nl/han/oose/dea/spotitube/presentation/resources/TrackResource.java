package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.services.TokenServiceInterface;
import nl.han.oose.dea.spotitube.domain.services.TrackServiceInterface;
import nl.han.oose.dea.spotitube.presentation.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private TrackServiceInterface trackService;
    private TokenServiceInterface tokenService;

    @Inject
    public void setTrackService(TrackServiceInterface trackService) { this.trackService = trackService; }

    @Inject
    public void setTokenService(TokenServiceInterface tokenService) { this.tokenService = tokenService; }

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
