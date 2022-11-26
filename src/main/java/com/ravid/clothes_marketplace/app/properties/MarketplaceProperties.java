package com.ravid.clothes_marketplace.app.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "clothes-marketplace")
@Getter @Setter
public class MarketplaceProperties {
    private List<String> authorizationRequiredUris;

}
