package nl.han.oose.dea.spotitube.presentation.mappers;

import nl.han.oose.dea.spotitube.domain.exceptions.ForbiddenRequestException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenRequestExceptionMapper implements ExceptionMapper<ForbiddenRequestException> {

    @Override
    public Response toResponse(ForbiddenRequestException e) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
