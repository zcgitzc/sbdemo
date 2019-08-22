package com.zark.sbproject.boot.web.permission.aspect;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;
import com.zark.sbproject.boot.web.permission.aspect.filter.AuthorityFilterChain;
import com.zark.sbproject.boot.web.permission.aspect.filter.FilterChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
* @desc
* @author zark
* @date 2019-08-22
*/
@Aspect
@Component
@Slf4j
public class AclCheckAspect {

    @Resource
    private PermissionService permissionService;

    @Resource
    private BucService bucService;

    @Pointcut("execution(@(com.zark.sbproject.boot.web.permission.anno.NeedLogin || com.zark.sbproject.boot.web.permission.anno.NeedPermissions" +
            "|| com.zark.sbproject.boot.web.permission.anno.NeedOneOfRoles || com.zark.sbproject.boot.web.permission.anno.NeedRoles" +
            "|| com.zark.sbproject.boot.web.permission.anno.NeedLogin) * *(..))")
    public void AuthorityPoint() {
    }

    @Around(value = "AuthorityPoint()")
    public Object process(ProceedingJoinPoint jp) throws Throwable {

        MethodSignature joinPointObject = (MethodSignature) jp.getSignature();
        Method method = joinPointObject.getMethod();

        AuthorityFilterChain authorityFilterChain = FilterChainFactory.INSTANCE.getFilterChain();

        authorityFilterChain.doCheck(method, permissionService, bucService);

        try {
            return jp.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof InvocationTargetException) {
                //抛出原始异常
                InvocationTargetException targetException = (InvocationTargetException) throwable;
                throw targetException.getTargetException();
            }
            throw throwable;
        }
    }
}
