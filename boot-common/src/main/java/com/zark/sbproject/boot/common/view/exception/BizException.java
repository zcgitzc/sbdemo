package com.zark.sbproject.boot.common.view.exception;


import java.io.Serializable;

/**
* @desc 业务异常
* @author zark
* @date 2019-08-22
*/
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERROR_ERROR_CODE = "500";
    private static final String DEFAULT_ERROR_ERROR_MSG = "系统异常.";

    protected String errorCode;

    protected String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BizException(IExceptionCode code) {
        super(ExceptionHelper.getMessage(code));
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = this.getMessage();
    }

    public BizException(IExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = ExceptionHelper.getMessage(code);
    }

    public BizException(IExceptionCode code, String message) {
        super(message);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = ExceptionHelper.getMessage(code);
    }

    public BizException(IExceptionCode code, Throwable cause) {
        super(ExceptionHelper.getMessage(code), cause);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = this.getMessage();
    }

    public BizException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = DEFAULT_ERROR_ERROR_CODE;
    }

    public BizException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BizException(String errorMessage, Throwable cause) {
        super(errorMessage,cause);
        this.errorCode = DEFAULT_ERROR_ERROR_CODE;
        this.errorMessage = errorMessage;
    }
}
