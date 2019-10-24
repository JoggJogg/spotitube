package nl.han.oose.dea.spotitube.presentation.mappers;

import nl.han.oose.dea.spotitube.domain.exceptions.MissingTokenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingTokenExceptionMapper implements ExceptionMapper<MissingTokenException> {

    @Override
    public Response toResponse(MissingTokenException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
