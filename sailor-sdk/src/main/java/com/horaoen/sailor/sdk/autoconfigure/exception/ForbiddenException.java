package com.horaoen.sailor.sdk.autoconfigure.exception;

import com.horaoen.sailor.sdk.autoconfigure.bean.Code;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
public class ForbiddenException extends HttpException{
    public ForbiddenException() {
        super(Code.FORBIDDEN.getDescription(), Code.FORBIDDEN.getCode());
        this.code = Code.FORBIDDEN.getCode();
        this.httpCode = HttpStatus.FORBIDDEN.value();
    }

    public ForbiddenException(String message, int code) {
        super(message, code, HttpStatus.FORBIDDEN.value());
    }

    public ForbiddenException(String message) {
        super(message, Code.FORBIDDEN.getCode(), HttpStatus.FORBIDDEN.value());
    }

    public ForbiddenException(int code) {
        super(Code.FORBIDDEN.getDescription(), code, HttpStatus.FORBIDDEN.value());
    }

}
