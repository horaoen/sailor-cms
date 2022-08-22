package com.horaoen.sailor.web.vo;

import com.horaoen.sailor.sdk.autoconfigure.bean.Code;
import com.horaoen.sailor.sdk.autoconfigure.util.RequestUtil;
import com.horaoen.sailor.web.common.configuration.CodeMessageConfiguration;
import com.horaoen.sailor.web.common.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
@Data
@Builder
@AllArgsConstructor
public class UnifyResponseVo<T> {
    private Integer code;
    private T data;
    private Object message;
    private String request;

    public UnifyResponseVo() {
        this.code = Code.SUCCESS.getCode();
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(int code) {
        this.code = code;
        this.message = CodeMessageConfiguration.getMessage(code);
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(T message) {
        this.code = Code.SUCCESS.getCode();
        this.message = message;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(int code, T message) {
        this.code = code;
        this.message = message;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(HttpStatus httpStatus) {
        this.code = Code.SUCCESS.getCode();
        this.message = Code.SUCCESS.getDescription();
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

}
