package com.zark.sbproject.boot.service.common.util;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.zark.sbproject.boot.service.common.service.BucService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zark
 * @description
 * @date 2019-08-24 14:55
 */
@Aspect
@Component
public class DalAop {

    private static final String CREATOR = "creator";
    private static final String MODIFIER = "modifier";
    private static final String GMT_CREATE = "gmtCreate";
    private static final String GMT_MODIFIED = "gmtModified";

    @Resource
    private BucService bucService;

    @Pointcut("execution(* com.zark.sbproject.boot.dao.*.mapper.*.insert*(..))" +
            "|| execution(* com.zark.sbproject.boot.dao.*.mapper.*.add*(..))" +
            "|| execution(* com.zark.sbproject.boot.dao.*.mapper.*.save*(..))")
    public void beforeInsert() {
    }

    @Pointcut("execution(* com.zark.sbproject.boot.dao.*.mapper.*.update*(..))" +
            "|| execution(* com.zark.sbproject.boot.dao.*.mapper.*.delete*(..))" +
            "|| execution(* com.zark.sbproject.boot.dao.*.mapper.*.remove*(..))")
    public void beforeUpdate() {
    }


    @Before("beforeInsert()")
    public void beforeInsert(JoinPoint jp) {
        Object[] args = jp.getArgs();

        if (args != null && args.length > 0) {
            Object argument = args[0];
            BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
            // 设置创建时间和修改时间
            if (beanWrapper.isWritableProperty(GMT_CREATE)) {
                beanWrapper.setPropertyValue(GMT_CREATE, new Date());
            }
            if (beanWrapper.isWritableProperty(GMT_MODIFIED)) {
                beanWrapper.setPropertyValue(GMT_MODIFIED, new Date());
            }
            // 设置创建人和修改人
            if (beanWrapper.isWritableProperty(CREATOR) && bucService != null
                    && StringUtils.hasLength(bucService.getCurrUsername())) {
                beanWrapper.setPropertyValue(CREATOR, bucService.getCurrUsername());
            }
            if (beanWrapper.isWritableProperty(MODIFIER) && bucService != null
                    && StringUtils.hasLength(bucService.getCurrUsername())) {
                beanWrapper.setPropertyValue(MODIFIER, bucService.getCurrUsername());
            }
        }
    }

    @Before("beforeUpdate()")
    public void beforeUpdate(JoinPoint jp) {

        Object[] args = jp.getArgs();
        // myBatis只能传递一个参数
        if (args != null && args.length > 0) {
            Object argument = args[0];

            // 如果argument是map类型
            if (argument instanceof Map) {
                Map map = (Map) argument;
                map.put(GMT_MODIFIED, new Date());
                if (bucService != null && StringUtils.hasLength(bucService.getCurrUsername())) {
                    map.put(MODIFIER, bucService.getCurrUsername());
                }
            } else {
                BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
                if (beanWrapper.isWritableProperty(GMT_MODIFIED)) {
                    beanWrapper.setPropertyValue(GMT_MODIFIED, new Date());
                }
                if (beanWrapper.isWritableProperty(MODIFIER) && bucService != null
                        && StringUtils.hasLength(bucService.getCurrUsername())) {
                    beanWrapper.setPropertyValue(MODIFIER, bucService.getCurrUsername());
                }
            }
        }
    }

}
