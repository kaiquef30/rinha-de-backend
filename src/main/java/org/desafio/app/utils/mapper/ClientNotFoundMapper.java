package org.desafio.app.utils.mapper;

import org.desafio.app.utils.exceptions.ClientNotFoundException;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientNotFoundMapper implements ExceptionMapper<ClientNotFoundException> {

    @Override
    public Response toResponse(ClientNotFoundException exception) {
        return Response.status(HttpStatus.NOT_FOUND_404)
                .entity(new ResponseMapper(HttpStatus.NOT_FOUND_404, ResponseMapper.ErrorCode.CLIENT_NOT_FOUND, exception.getMessage()))
                .build();
    }
}
