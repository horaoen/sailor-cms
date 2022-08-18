package com.horaoen.sailor.sdk.autoconfigure.exception;

import com.horaoen.sailor.sdk.autoconfigure.bean.Code;
import com.horaoen.sailor.sdk.autoconfigure.interfaces.BaseResponse;
import org.springframework.http.HttpStatus;

/**
 * @author horaoen
 */
public class HttpException extends RuntimeException implements BaseResponse {
    protected int httpCode;
    protected int code;
    protected boolean messageOnly;

    public HttpException() {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = Code.INTERNAL_SERVER_ERROR.getCode();
        this.messageOnly = false;
    }

    public HttpException(String message) {
        super(message);
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = Code.INTERNAL_SERVER_ERROR.getCode();
        this.messageOnly = true;
    }

    public HttpException(int code) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.messageOnly = false;
        this.code = code;
    }

    public HttpException(int code, int httpCode) {
        super(Code.INTERNAL_SERVER_ERROR.getDescription());
        this.messageOnly = false;
        this.httpCode = httpCode;
        this.code = code;
    }

    public HttpException(String message, int code) {
        super(message);
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.messageOnly = false;
        this.code = code;
    }

    public HttpException(String message, int code, int httpCode) {
        super(message);
        this.messageOnly = false;
        this.httpCode = httpCode;
        this.code = code;
    }

    public HttpException(Throwable cause, int code) {
        super(cause);
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.messageOnly = false;
        this.code = code;
    }

    public HttpException(Throwable cause, int code, int httpCode) {
        super(cause);
        this.messageOnly = false;
        this.code = code;
        this.httpCode = httpCode;
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
        this.httpCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.code = Code.INTERNAL_SERVER_ERROR.getCode();
        this.messageOnly = false;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public boolean isMessageOnly() {
        return this.messageOnly;
    }

    @Override
    public int getHttpCode() {
        return this.httpCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
