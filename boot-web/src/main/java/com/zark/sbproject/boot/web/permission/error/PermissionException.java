package com.zark.sbproject.boot.web.permission.error;



import com.zark.sbproject.boot.common.view.exception.ExceptionHelper;
import com.zark.sbproject.boot.common.view.exception.IExceptionCode;

import java.io.Serializable;

/**
* @desc
* @author zark
* @date 2019-08-22 23:26
*/
public class PermissionException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERROR_ERROR_CODE = "500";
    private static final String DEFAULT_ERROR_ERROR_MSG = "权限异常!";

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

    public PermissionException(IExceptionCode code) {
        super(ExceptionHelper.getMessage(code));
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = this.getMessage();
    }

    public PermissionException(IExceptionCode code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = ExceptionHelper.getMessage(code);
    }

    public PermissionException(IExceptionCode code, String message) {
        super(message);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = ExceptionHelper.getMessage(code);
    }

    public PermissionException(IExceptionCode code, Throwable cause) {
        super(ExceptionHelper.getMessage(code), cause);
        this.errorCode = ExceptionHelper.getCode(code);
        this.errorMessage = this.getMessage();
    }

    public PermissionException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = DEFAULT_ERROR_ERROR_CODE;
    }

    public PermissionException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public PermissionException(String errorMessage, Throwable cause) {
        super(errorMessage,cause);
        this.errorCode = DEFAULT_ERROR_ERROR_CODE;
        this.errorMessage = errorMessage;
    }
}
