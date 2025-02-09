package org.desafio.app.utils.exceptions;

public class InvalidBalance extends RuntimeException {
    public InvalidBalance(String message) {
        super(message);
    }
}
