package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces("application/json")
    public Response getAvailableTracks(@QueryParam("forPlaylist") int playlist, @QueryParam("token") String token) {
        if(token == null) return Response.status(400).build();
        TracksDTO tracks = trackService.getAvailableTracks(playlist);
        return Response
                .status(200)
                .entity(tracks)
                .build();
    }
}
