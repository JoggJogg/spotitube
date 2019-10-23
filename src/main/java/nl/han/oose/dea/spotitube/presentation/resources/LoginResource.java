package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationService;
import nl.han.oose.dea.spotitube.domain.pojo.Token;
import nl.han.oose.dea.spotitube.domain.pojo.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private AuthenticationService itemService;

    @Inject
    public void setItemService(AuthenticationService itemService) {
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
                    .status(Response.Status.CREATED)
                    .entity(token)
                    .build();
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(null)
                .build();
    }
}
