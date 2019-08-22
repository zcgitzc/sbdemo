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
public @interface NeedOneOfRoles {

    /**
     * 只要有其中一个角色, 就可以访问此方法
     * @return
     */
    String[] roles();
}
