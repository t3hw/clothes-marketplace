package com.ravid.clothes_marketplace.app.service.interceptors;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ravid.clothes_marketplace.app.properties.MarketplaceProperties;

public class IncomingRequestInterceptor implements HandlerInterceptor, ApplicationContextAware {

    private static MarketplaceProperties props;
    private static ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
        List<String> authURIs = props.getAuthorizationRequiredUris();
        Predicate<String> authorizationRequestFilter = (x) -> false;

        // Building a filter for the request URI
        for (String uri : authURIs) {
            authorizationRequestFilter = authorizationRequestFilter.or(requestUri -> requestUri.startsWith(uri));
        }

        RequestScopeData RequestData = context.getBean(RequestScopeData.class,request.getMethod());

        // Only validate jwt of request URIs matching the filter. Unauthorized exception will be thrown if JWT is missing, invalid, or the user is unauthorized
        String jwt = null;
        if (Optional.of(request.getRequestURI()).filter(authorizationRequestFilter).isPresent()) {
            jwt = request.getHeader("Authorization");
            RequestData.verifyToken(Optional.ofNullable(jwt));
        }
            
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
      throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
      throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext) {
        if (context == null) {
            context = applicationContext;
            props = context.getBean(MarketplaceProperties.class);
        }
    }
}