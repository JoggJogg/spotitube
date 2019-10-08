package nl.han.oose.dea.spotitube.remote;

import nl.han.oose.dea.spotitube.domain.TokenDTO;
import nl.han.oose.dea.spotitube.domain.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static nl.han.oose.dea.spotitube.datasource.LoginDAO.correctLogin;

@Path("/login")
public class LoginResource {

    private static final int HTTP_CREATED = 201;
    private static final int HTTP_BAD_REQUEST = 400;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(UserDTO user) {
        if(correctLogin(user)) {
            return Response
                    .status(HTTP_CREATED)
                    .entity(new TokenDTO(user))
                    .build();
        } else {
            return Response
                    .status(HTTP_BAD_REQUEST)
                    .entity(null)
                    .build();
        }
    }
}
