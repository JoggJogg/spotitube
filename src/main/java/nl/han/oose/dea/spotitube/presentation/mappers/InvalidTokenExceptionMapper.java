package nl.han.oose.dea.spotitube.presentation.mappers;

import nl.han.oose.dea.spotitube.domain.exceptions.InvalidTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTokenExceptionMapper implements ExceptionMapper<InvalidTokenException> {

    @Override
    public Response toResponse(InvalidTokenException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
