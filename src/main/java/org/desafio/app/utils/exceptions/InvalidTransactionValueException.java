package org.desafio.app.utils.exceptions;

public class InvalidTransactionValueException extends RuntimeException {
  public InvalidTransactionValueException() {
    super("Transação inválida: valor da transação deve ser um número inteiro.");
  }
}
