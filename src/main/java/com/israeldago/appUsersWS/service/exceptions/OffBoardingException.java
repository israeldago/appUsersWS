package com.israeldago.appUsersWS.service.exceptions;

public class OffBoardingException extends RuntimeException{
    public OffBoardingException() {
    }

    public OffBoardingException(String s) {
        super(s);
    }

    public OffBoardingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public OffBoardingException(Throwable throwable) {
        super(throwable);
    }

    public OffBoardingException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
