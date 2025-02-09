package org.desafio.app.utils.exceptions;

public class ClientNotFound extends RuntimeException {
    public ClientNotFound(String message) {
        super(message);
    }
}
