package nl.han.oose.dea.spotitube;

import nl.han.oose.dea.spotitube.domain.TokenDTO;
import nl.han.oose.dea.spotitube.domain.UserDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static nl.han.oose.dea.spotitube.datasource.LoginDAO.correctLogin;

@Path("/login")
public class LoginResource {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(UserDTO user) {
        if(correctLogin(user)) {
            return Response
                    .status(201)
                    .entity(new TokenDTO(user))
                    .build();
        } else {
            return Response
                    .status(400)
                    .entity(null)
                    .build();
        }
    }
}
