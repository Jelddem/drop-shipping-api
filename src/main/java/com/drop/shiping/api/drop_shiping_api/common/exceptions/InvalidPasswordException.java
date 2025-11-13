package com.drop.shiping.api.drop_shiping_api.common.exceptions;

public class InvalidPasswordException extends RuntimeException {
    private static final String errorCode = "PASSWORD_UNAUTHORIZED";

    public InvalidPasswordException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }
}
