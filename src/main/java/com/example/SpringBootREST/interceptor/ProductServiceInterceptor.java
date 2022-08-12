package com.example.SpringBootREST.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;

/**
 * Interceptor Class
 *
 * Perform operations under the following situations ~
 *  - before sending a request to the controller
 *  - before sending the response to the client
 *
 * @author Eejay Taa
 */

@Component
public class ProductServiceInterceptor implements HandlerInterceptor{

    Logger logger = LoggerFactory.getLogger(ProductServiceInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("ProductServiceInterceptor is running.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
