package com.ravid.clothes_marketplace.app.properties;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "clothes-marketplace")
@Setter
public class MarketplaceProperties {
    private List<String> authorizationRequiredUris;
    private List<String> authorizationAllowedUris;

    public Predicate<String> getAuthRequestFilter() {
        Predicate<String> authorizationRequestFilter = (x) -> false;

        // Building a filter for the request URI
        for (String uri : authorizationRequiredUris) {
            authorizationRequestFilter = authorizationRequestFilter.or(requestUri -> requestUri.startsWith(uri));
        }
        return authorizationRequestFilter;
    }

    public String[] getAllowdUris(){
        return authorizationAllowedUris.toArray(new String[0]);
    }

}
