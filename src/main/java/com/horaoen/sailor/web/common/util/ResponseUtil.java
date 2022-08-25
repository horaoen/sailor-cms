package com.horaoen.sailor.web.common.util;

import com.horaoen.sailor.autoconfigure.bean.Code;
import com.horaoen.sailor.autoconfigure.util.RequestUtil;
import com.horaoen.sailor.web.vo.message.UnifyResponseVo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author horaoen
 */

public class ResponseUtil {
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void setCurrentResponseHttpStatus(int httpStatus) {
        getResponse().setStatus(httpStatus);
    }

    public static <T> UnifyResponseVo<T> generateCreatedResponse(int code) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(Code.CREATED.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateCreatedResponse(int code, T data) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateDeletedResponse(int code) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(Code.SUCCESS.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateDeletedResponse(int code, T data) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateUpdatedResponse(int code) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(Code.SUCCESS.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateUpdatedResponse(int code, T data) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .data(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVo<T> generateUnifyResponse(int code) {
        return (UnifyResponseVo<T>) UnifyResponseVo.builder()
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }
    
}
