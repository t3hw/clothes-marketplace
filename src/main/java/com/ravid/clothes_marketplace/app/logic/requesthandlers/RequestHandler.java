package com.ravid.clothes_marketplace.app.logic.requesthandlers;

import org.springframework.http.ResponseEntity;

public abstract class RequestHandler {
    public RequestHandler(Object[] args) {};

    public abstract <T> ResponseEntity<T> handleRequest();
}
