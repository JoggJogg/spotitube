package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.domain.Track;
import nl.han.oose.dea.spotitube.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists/{playlistId}/tracks")
public class PlaylistTrackResource {

    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces("application/json")
    public Response getAllTracksFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId) {
        return Response
                .status(200)
                .entity(trackService.getAllTracksFromPlaylist(playlistId))
                .build();
    }

    @DELETE
    @Path("/{trackId}")
    @Produces("application/json")
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        trackService.removeTrackFromPlaylist(trackId, playlistId);
        return Response
                .status(200)
                .entity(trackService.getAllTracksFromPlaylist(playlistId))
                .build();
    }

    @POST
    @Produces("application/json")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, Track track) {
        trackService.addTrackToPlaylist(track, playlistId);
        return Response
                .status(200)
                .entity(trackService.getAllTracksFromPlaylist(playlistId))
                .build();
    }


}
