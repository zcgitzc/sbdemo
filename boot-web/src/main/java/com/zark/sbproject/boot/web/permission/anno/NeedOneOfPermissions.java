package com.zark.sbproject.boot.web.permission.anno;

import java.lang.annotation.*;

/**
* @desc 
* @author zark
* @date 2019-08-22
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedOneOfPermissions {

    /**
     * 需要有以下全部权限, 才能访问
     * @return
     */
    String[] permissions();
}
