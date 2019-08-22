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
public @interface NeedLogin {

}
