package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.InsufficientBalanceException;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InsufficientBalanceMapper implements ExceptionMapper<InsufficientBalanceException> {

    @Override
    public Response toResponse(InsufficientBalanceException exception) {
        return Response.status(HttpStatus.FORBIDDEN_403)
                .entity(new ResponseMapper(HttpStatus.FORBIDDEN_403, ResponseMapper.ErrorCode.INSUFFICIENT_BALANCE, exception.getMessage()))
                .build();
    }
}
