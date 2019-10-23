package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.data.LoginCredentialsDataMapper;
import nl.han.oose.dea.spotitube.domain.services.LoginService;
import nl.han.oose.dea.spotitube.domain.Token;
import nl.han.oose.dea.spotitube.domain.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/login")
public class LoginResource {

    private LoginService itemService;

    private static final int HTTP_CREATED = 201;
    private static final int HTTP_BAD_REQUEST = 400;

    @Inject
    public void setItemService(LoginService itemService) {
        this.itemService = itemService;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(User user) {
        if(itemService.correctLogin(user)) {
            Token token = new Token(user);
            LocalStorage localStorage = LocalStorage.getInstance();
            localStorage.setToken(token);
            return Response
                    .status(HTTP_CREATED)
                    .entity(token)
                    .build();
        }
        return Response
                .status(HTTP_BAD_REQUEST)
                .entity(null)
                .build();
    }
}
