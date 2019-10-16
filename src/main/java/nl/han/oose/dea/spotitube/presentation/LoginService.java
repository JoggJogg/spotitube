package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.Token;
import nl.han.oose.dea.spotitube.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static nl.han.oose.dea.spotitube.data.LoginCredentialsDataMapper.correctLogin;

@Path("/login")
public class LoginService {

    private static final int HTTP_CREATED = 201;
    private static final int HTTP_BAD_REQUEST = 400;

    LocalStorage storage = new LocalStorage();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(User user) {
        if(correctLogin(user)) {
            Token token = new Token(user);
            storage.add(token);
            return Response
                    .status(HTTP_CREATED)
                    .entity(token)
                    .build();
        } else {
            return Response
                    .status(HTTP_BAD_REQUEST)
                    .entity(null)
                    .build();
        }
    }
}
