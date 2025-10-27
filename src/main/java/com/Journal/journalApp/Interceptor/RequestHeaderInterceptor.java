package com.Journal.journalApp.Interceptor;

import com.Journal.journalApp.exception.InvalidHeaderFieldException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class RequestHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       System.out.println("hi");
        return true;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse
//            response, Object handle ) throws Exception {
//        System.out.println("Post Handle method is Calling");
//    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception) throws
            Exception {
        System.out.println("today");
    }

}
