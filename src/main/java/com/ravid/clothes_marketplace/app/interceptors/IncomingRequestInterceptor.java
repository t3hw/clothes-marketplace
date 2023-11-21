package com.ravid.clothes_marketplace.app.interceptors;

import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ravid.clothes_marketplace.app.properties.MarketplaceProperties;
import com.ravid.clothes_marketplace.app.security.JwtUtil;

public class IncomingRequestInterceptor implements HandlerInterceptor, ApplicationContextAware {


    @Autowired 
    private JwtUtil jwtUtil;

    @Autowired
    private MarketplaceProperties props;
    private ApplicationContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
      throws Exception {
        
        // Save crucial data to request scope bean for access later down the processing pipeline
        RequestScopeData requestData = context.getBean(RequestScopeData.class).init(request.getRequestURI());
        
        // Only validate jwt of request URIs matching the filter. Unauthorized exception will be thrown if JWT is missing, invalid, or the user is unauthorized
        String jwt = null;
        if (Optional.of(request.getRequestURI()).filter(props.getAuthRequestFilter()).isPresent()) {
            jwt = request.getHeader("Authorization").substring(7);
            var userId = jwtUtil.decodeToken(jwt).getSubject();
            requestData.setUserId(userId);
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
        this.context = applicationContext;
    }
    
}