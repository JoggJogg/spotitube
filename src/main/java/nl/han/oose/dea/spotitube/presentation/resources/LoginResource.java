package nl.han.oose.dea.spotitube.presentation.resources;

import nl.han.oose.dea.spotitube.data.LocalStorage;
import nl.han.oose.dea.spotitube.domain.pojo.Token;
import nl.han.oose.dea.spotitube.domain.pojo.User;
import nl.han.oose.dea.spotitube.domain.services.AuthenticationServiceInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    private AuthenticationServiceInterface authenticationService;
    private LocalStorage localStorage;

    @Inject
    public void setAuthenticationService(AuthenticationServiceInterface authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Inject
    public void setLocalStorage(LocalStorage localStorage) { this.localStorage = localStorage; }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(User user) {
        Token token = new Token(user);
        authenticationService.login(user);
        localStorage.setToken(token);
        return Response
                .status(Response.Status.CREATED)
                .entity(token)
                .build();
    }
}
