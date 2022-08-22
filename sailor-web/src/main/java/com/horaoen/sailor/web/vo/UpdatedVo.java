package com.horaoen.sailor.web.vo;

import com.horaoen.sailor.autoconfigure.bean.Code;
import com.horaoen.sailor.web.common.util.ResponseUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
@Data
public class UpdatedVo<T> extends UnifyResponseVo<T>{
    public UpdatedVo() {
        super(Code.UPDATED.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedVo(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedVo(T message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedVo(int code, T message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }
}
