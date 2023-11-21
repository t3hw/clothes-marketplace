package com.ravid.clothes_marketplace.app.logic.requesthandlers;

import org.springframework.http.ResponseEntity;
/*
 * Abstract request handler for dynamic request mapping and handling by the services
 */
public abstract class RequestHandler {
    public abstract <T> ResponseEntity<T> handleRequest();
}
