package com.israeldago.appUsersWS.service.exceptions;

public class CredentialsCheckException extends RuntimeException {
    public CredentialsCheckException() {
    }

    public CredentialsCheckException(String s) {
        super(s);
    }

    public CredentialsCheckException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CredentialsCheckException(Throwable throwable) {
        super(throwable);
    }

    public CredentialsCheckException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
