package com.horaoen.sailor.web.vo.message;

import com.horaoen.sailor.autoconfigure.bean.Code;
import com.horaoen.sailor.web.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
public class CreatedVo<T> extends UnifyResponseVo{
    public CreatedVo() {
        super(Code.CREATED.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedVo(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedVo(T message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedVo(int code, T message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }
}
