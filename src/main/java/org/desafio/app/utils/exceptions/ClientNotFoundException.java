package org.desafio.app.utils.exceptions;


public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("Cliente não encontrado na base de dados");
    }
}
