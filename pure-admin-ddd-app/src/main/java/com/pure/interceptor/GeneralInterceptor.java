package com.pure.interceptor;

import com.pure.interceptor.service.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Component
public class GeneralInterceptor implements HandlerInterceptor {
    private final TraceService traceService;

    /**
     * PreHandle is called before the controller is called
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @return boolean
     * @author zzx
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        traceService.registerInitTime(request);
        return true;
    }

    /**
     * PostHandle is called after the controller is called
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param handler  Object
     * @param ex       Exception
     * @author zzx
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        traceService.registerTrace(request, response);
    }
}
