package nl.han.oose.dea.spotitube.rest;

import nl.han.oose.dea.spotitube.data.AbstractMapper;
import nl.han.oose.dea.spotitube.data.PlaylistDataMapper;
import nl.han.oose.dea.spotitube.presentation.PlaylistsDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistService {

    private AbstractMapper dataMapper;

    @Inject
    public void setDataMapper(PlaylistDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        return Response
                .status(200)
                .entity(new PlaylistsDTO(dataMapper.findAll(), 1234))
                .build();
    }
}
