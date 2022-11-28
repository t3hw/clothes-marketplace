package com.ravid.clothes_marketplace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ravid.clothes_marketplace.app.interceptors.IncomingRequestInterceptor;

@EnableWebMvc
@Configuration
public class WebConfigurator implements WebMvcConfigurer {
    @Bean
    public IncomingRequestInterceptor bearerTokenAuthInterceptor() {
        return new IncomingRequestInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(bearerTokenAuthInterceptor());
    }
}
