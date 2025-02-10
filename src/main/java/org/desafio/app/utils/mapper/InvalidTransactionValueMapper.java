package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.InvalidTransactionValueException;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidTransactionValueMapper implements ExceptionMapper<InvalidTransactionValueException> {

    @Override
    public Response toResponse(InvalidTransactionValueException exception) {
        return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422)
                .entity(new ResponseMapper(HttpStatus.UNPROCESSABLE_ENTITY_422, ResponseMapper.ErrorCode.INVALID_TRANSACTION_VALUE, exception.getMessage()))
                .build();
    }
}
