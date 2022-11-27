package com.ravid.clothes_marketplace.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

// Openapi swagger-ui page configuration
@Configuration
public class SwaggerConfig {
    final String securitySchemeName = "bearerAuth";

    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI oApi = new OpenAPI()
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .security(List.of(new SecurityRequirement().addList(securitySchemeName)))
                .info(new Info()
                        .title("Clothes Marketplace")
                        .version("1")
                        .contact(new Contact()
                                .name("Ravid Gontov")
                                .email("rvdgntv@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));

        return oApi;
    }

    // @Bean
    // public OperationCustomizer customize() {
    // return (operation, handlerMethod) -> operation.addParametersItem(
    // new Parameter()
    // .in("header")
    // .required(true)
    // .description("bearerToken")
    // .name("Authorization"));
    // }
}