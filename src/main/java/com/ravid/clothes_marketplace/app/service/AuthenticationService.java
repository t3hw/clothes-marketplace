package com.ravid.clothes_marketplace.app.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ravid.clothes_marketplace.app.interceptors.RequestScopeData;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.api.AuthenticateApiDelegate;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;

@Service
public class AuthenticationService implements AuthenticateApiDelegate, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            AuthenticationRequestDTO authenticationRequestDTO) {
        String getOperationName = context.getBean(RequestScopeData.class).getOperationName();
        return ((RequestHandler) context.getBean(getOperationName, authenticationRequestDTO)).handleRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
