package com.horaoen.sailor.web.vo.message;

import com.horaoen.sailor.autoconfigure.bean.Code;
import com.horaoen.sailor.web.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
public class DeletedVo<T> extends UnifyResponseVo<T> {

    public DeletedVo() {
        super(Code.DELETED.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedVo(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedVo(T message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedVo(int code, T message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

}
