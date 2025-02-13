package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.InsufficientBalanceException;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InsufficientBalanceMapper implements ExceptionMapper<InsufficientBalanceException> {

    @Override
    public Response toResponse(InsufficientBalanceException exception) {
        return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422)
                .entity(new ResponseMapper(HttpStatus.UNPROCESSABLE_ENTITY_422, ResponseMapper.ErrorCode.INSUFFICIENT_BALANCE, exception.getMessage()))
                .build();
    }
}
