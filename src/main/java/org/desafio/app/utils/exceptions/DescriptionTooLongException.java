package org.desafio.app.utils.exceptions;


public class DescriptionTooLongException extends RuntimeException {
    public DescriptionTooLongException() {
        super("Descrição muito longa");
    }
}
