package com.zark.sbproject.boot.web.framework;

import com.zark.sbproject.boot.common.view.exception.BizException;
import com.zark.sbproject.boot.common.view.exception.ServiceException;
import com.zark.sbproject.boot.common.view.response.WebResult;
import com.zark.sbproject.boot.web.permission.error.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class WebResultHandler implements ResponseBodyAdvice {

    private static final Class[] annos = {
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class};


    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return WebResult.success(null);
        }
        if (body instanceof WebResult) {
            return body;
        } else {
            return WebResult.success(body);
        }

    }

    @ExceptionHandler(PermissionException.class)
    @ResponseBody
    public WebResult<String> handleNoPermission(HttpServletRequest req, Throwable exception, HttpServletResponse response) throws IOException {
        log.error("handleServiceRuntime, req:" + req.getRequestURI(), exception);

        PermissionException permissionException = null;
        if (exception instanceof PermissionException) {
            permissionException = (PermissionException) exception;
        } else {
            return null;
        }

        String applyUrl = "apply acl url by exception code";

        return WebResult.fail(WebResult.NO_PERMISSION_CODE, "无权限访问!", applyUrl);
    }


    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public WebResult<String> handleServiceRuntime(HttpServletRequest req, Throwable exception) {
        log.error("handleServiceRuntime, req:" + req.getRequestURI(), exception);

        ServiceException serviceException = (ServiceException) exception;
        return WebResult.fail(serviceException.getErrorCode(), serviceException.getErrorMessage());
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public WebResult<String> handleBizRuntime(HttpServletRequest req, Throwable exception) {
        log.error("handleBizRuntime, req:" + req.getRequestURI(), exception);

        BizException bizException = (BizException) exception;
        return WebResult.fail(bizException.getErrorCode(), bizException.getErrorMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public WebResult<String> handleThrowable(HttpServletRequest req, Throwable exception) {
        log.error("handleThrowable, req:" + req.getRequestURI(), exception);

        return WebResult.fail("未知异常异常,异常类型:" + exception.getClass().getSimpleName() + " mes:" + exception.getMessage());
    }

}
