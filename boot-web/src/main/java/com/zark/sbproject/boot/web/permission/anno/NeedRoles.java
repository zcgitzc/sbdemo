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
public @interface NeedRoles {

    /**
     * 需要有以下全部角色, 才能访问此接口
     * @return
     */
    String[] roles();
}
