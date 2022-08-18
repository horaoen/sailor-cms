package com.horaoen.sailor.sdk.autoconfigure.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author horaoen
 */
public class RequestUtil {
    public RequestUtil() {
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getRequestUrl() {
        return getRequest().getServletPath();
    }

    public static String getSimpleRequest(HttpServletRequest request) {
        return String.format("%s %s", request.getMethod(), request.getServletPath());
    }

    public static String getSimpleRequest() {
        HttpServletRequest request = getRequest();
        return String.format("%s %s", request.getMethod(), request.getServletPath());
    }
}
