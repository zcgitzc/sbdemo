package com.zark.sbproject.boot.common.view.response;

import java.io.Serializable;

/**
 * @author zark
 * @date 2019-08-22 22:54:24
 */
public class WebResult<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String SUCCESS_CODE = "200";
    private static final String FAILED_CODE = "500";
    public static final String NO_PERMISSION_CODE = "NO_PERMISSION";
    private static final String FAIL_VALUE = "fail";
    private static final String SUCCESS_VALUE = "success";

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 结果码
     */
    private String errorCode;

    /**
     * 结果描述
     */
    private String errorMsg;

    private E content;

    public WebResult(boolean success, String errorCode, String errorMsg, E content) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.content = content;
    }

    public static <E> WebResult<E> success(String errorCode, String errorMsg, E content) {
        WebResult<E> webResult = new WebResult(true, errorCode, errorMsg, content);

        return webResult;
    }

    public static <E> WebResult<E> success(E content) {
        WebResult<E> webResult = new WebResult(true, SUCCESS_CODE, SUCCESS_VALUE, content);

        return webResult;
    }

    public static <E> WebResult<E> fail(String errorCode, String errorMsg, E content) {
        WebResult<E> webResult = new WebResult(false, errorCode, errorMsg, content);

        return webResult;
    }

    public static <E> WebResult<E> fail(String errorMsg, E content) {
        WebResult<E> webResult = new WebResult(false, FAILED_CODE, errorMsg, content);

        return webResult;
    }

    public static <E> WebResult<E> fail(E content) {
        WebResult<E> webResult = new WebResult(false, FAILED_CODE, FAIL_VALUE, content);

        return webResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }
}
