package com.ravid.clothes_marketplace.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ravid.clothes_marketplace.server.api.HealthApiDelegate;

@Service
public class HealthService implements HealthApiDelegate {

    @Override
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok(null);
    }
}
