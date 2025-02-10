package org.desafio.app.utils.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Saldo insuficiente");
    }
}
