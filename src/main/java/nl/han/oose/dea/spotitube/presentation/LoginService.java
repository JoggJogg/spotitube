package nl.han.oose.dea.spotitube.presentation;

import nl.han.oose.dea.spotitube.data.LoginCredentialsDataMapper;
import nl.han.oose.dea.spotitube.domain.Token;
import nl.han.oose.dea.spotitube.domain.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/login")
public class LoginService {

    private LoginCredentialsDataMapper dataMapper;

    private static final int HTTP_CREATED = 201;
    private static final int HTTP_BAD_REQUEST = 400;

    @Inject
    public void setDataMapper(LoginCredentialsDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response handleLogin(User user) {
        try {
            if(dataMapper.correctLogin(user)) {
                Token token = new Token(user);
                return Response
                        .status(HTTP_CREATED)
                        .entity(token)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Response
                .status(HTTP_BAD_REQUEST)
                .entity(null)
                .build();
    }
}
