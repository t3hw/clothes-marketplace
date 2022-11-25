package com.ravid.clothes_marketplace.app.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BearerTokenAuthInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private ApplicationContext context;
    private static BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
        log.info("preHandle");
        log.info(request.getRequestURI());
        String jwt = request.getHeader("Authorization");
        context.getBean(ScopeJWTToken.class);
        
        throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Username and password do not match");

        // log.info(jwt);
        // return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
      throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
      throws Exception {
        log.info("afterCompletion");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}