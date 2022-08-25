package com.horaoen.sailor.web.vo.message;

import com.horaoen.sailor.autoconfigure.bean.Code;
import com.horaoen.sailor.autoconfigure.util.RequestUtil;
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

    public UnifyResponseVo(T data) {
        this.code = Code.SUCCESS.getCode();
        this.data = data;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(int code, T data) {
        this.code = code;
        this.data = data;
        this.request = RequestUtil.getSimpleRequest();
    }

    public UnifyResponseVo(HttpStatus httpStatus) {
        this.code = Code.SUCCESS.getCode();
        this.message = Code.SUCCESS.getDescription();
        this.request = RequestUtil.getSimpleRequest();
        ResponseUtil.setCurrentResponseHttpStatus(httpStatus.value());
    }

}
