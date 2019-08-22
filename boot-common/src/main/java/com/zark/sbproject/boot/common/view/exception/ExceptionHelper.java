package com.zark.sbproject.boot.common.view.exception;

import java.lang.reflect.Field;

/**
* @desc 解析Desc注解中的异常描述和code
* @author zark
* @date 2019-08-22
*/
public class ExceptionHelper {
    public static String getMessage(IExceptionCode code) {
        if (code == null){
            code = BaseExceptions.UNKNOWN_EXCEPTION;
        }
        try {
            Field field = code.getClass().getField(code.name());
            Desc descAnnotation = field.getAnnotation(Desc.class);
            if (descAnnotation == null){
                return code.name();
            }
            else if ("".equals(descAnnotation.value().trim())){
                return code.name();
            }
            else{
                return descAnnotation.value();
            }
        } catch (Throwable e) {
            return code.name();
        }
    }

    public static String getCode(IExceptionCode code) {
        if (code == null) {
            code = BaseExceptions.UNKNOWN_EXCEPTION;
        }
        try {
            Field field = code.getClass().getField(code.name());
            Desc descAnnotation = field.getAnnotation(Desc.class);
            if (descAnnotation == null){
                return code.name();
            }
            else if ("".equals(descAnnotation.value().trim())){
                return code.name();
            }
            else{
                return descAnnotation.code();
            }
        } catch (Throwable e) {
            return code.name();
        }
    }
}
