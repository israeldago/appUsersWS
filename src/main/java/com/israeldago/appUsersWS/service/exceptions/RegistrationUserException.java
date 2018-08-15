package com.israeldago.appUsersWS.service.exceptions;

public class RegistrationUserException extends RuntimeException{
    public RegistrationUserException() {
    }

    public RegistrationUserException(String s) {
        super(s);
    }

    public RegistrationUserException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RegistrationUserException(Throwable throwable) {
        super(throwable);
    }

    public RegistrationUserException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
