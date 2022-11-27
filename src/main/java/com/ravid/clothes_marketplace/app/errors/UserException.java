package com.ravid.clothes_marketplace.app.errors;

public class UserException extends RuntimeException{
    public UserException(String message, Throwable e) {
        super(message,e);
    }
    public UserException(Throwable e) {
        super(e);
    }
}
