package org.desafio.app.utils.exceptions;

public class InvalidTransactionType extends RuntimeException {
    public InvalidTransactionType() {
        super("Tipo de transação inválido");
    }
}
