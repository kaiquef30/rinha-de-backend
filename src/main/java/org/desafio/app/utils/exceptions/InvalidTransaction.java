package org.desafio.app.utils.exceptions;

public class InvalidTransaction extends RuntimeException {
    public InvalidTransaction(String message) {
        super(message);
    }
}
