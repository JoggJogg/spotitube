package nl.han.oose.dea.spotitube.presentation.mappers;

import nl.han.oose.dea.spotitube.domain.exceptions.UnauthorizedUserException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UnauthorizedUserExceptionMapper implements ExceptionMapper<UnauthorizedUserException> {

    @Override
    public Response toResponse(UnauthorizedUserException e) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
