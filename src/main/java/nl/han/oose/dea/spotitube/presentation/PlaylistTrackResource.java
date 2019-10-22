package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists/{id}/tracks")
public class PlaylistTrackResource {

    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces("application/json")
    public Response getAllTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int id) {
        return Response
                .status(200)
                .entity(trackService.getAllTracksFromPlaylist(id))
                .build();
    }


}
