package com.ravid.clothes_marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

// Openapi swagger-ui page configuration
@Configuration
public class SwaggerConfig 
{
    @Bean
    OpenAPI customOpenAPI() {
        OpenAPI oApi = new OpenAPI()
                .components(new Components())
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
}