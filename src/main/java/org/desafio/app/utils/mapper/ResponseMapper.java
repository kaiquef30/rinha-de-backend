package org.desafio.app.utils.mapper;

import java.io.Serializable;

public class ResponseMapper implements Serializable {

    public enum ErrorCode {
        CLIENT_NOT_FOUND,
        INVALID_TRANSACTION_TYPE,
        INSUFFICIENT_BALANCE,
        DESCRIPTION_TOO_LONG,
    }

    private final ErrorCode errorCode;
    private int status;
    private final String message;

    public ResponseMapper(int status, ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

}
