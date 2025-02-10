package org.desafio.app.utils.exceptions;

import javax.ws.rs.ext.Provider;

@Provider
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Cliente não encontrado na base de dados");
    }
}
