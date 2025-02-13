package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.InvalidTransactionType;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidTransactionTypeMapper implements ExceptionMapper<InvalidTransactionType> {

    @Override
    public Response toResponse(InvalidTransactionType exception) {
        return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422)
                .entity(new ResponseMapper(HttpStatus.UNPROCESSABLE_ENTITY_422, ResponseMapper.ErrorCode.INVALID_TRANSACTION_TYPE, exception.getMessage()))
                .build();
    }
}
