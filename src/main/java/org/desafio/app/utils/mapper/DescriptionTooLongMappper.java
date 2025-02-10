package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.DescriptionTooLongException;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DescriptionTooLongMappper implements ExceptionMapper<DescriptionTooLongException> {

    @Override
    public Response toResponse(DescriptionTooLongException exception) {
        return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422)
                .entity(new ResponseMapper(HttpStatus.UNPROCESSABLE_ENTITY_422, ResponseMapper.ErrorCode.DESCRIPTION_TOO_LONG, exception.getMessage()))
                .build();
    }
}
