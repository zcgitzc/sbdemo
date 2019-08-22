package com.zark.sbproject.boot.common.view.exception;

public enum BaseExceptions implements IExceptionCode {
    /**
     * 未知异常
     */
    @Desc(value = "未知异常", code = "10000")
    UNKNOWN_EXCEPTION,

    /**
     * 参数异常
     */
    @Desc(value = "参数异常", code = "20000")
    PARAM_ERROR,

    /**
     * 解密失败
     */
    @Desc(value = "解密失败", code = "30000")
    VERIFY_SIGN_ERROR,

    /**
     * 没有数据权限
     */
    @Desc(value = "没有数据权限", code = "40000")
    NO_DATA_PERMISSION,

    /**
     * 没有权限
     */
    @Desc(value = "没有权限", code = "50000")
    NO_PERMISSION,


}
