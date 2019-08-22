package com.zark.sbproject.boot.web.permission.aspect.filter;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;
import com.zark.sbproject.boot.web.permission.anno.NeedLogin;
import com.zark.sbproject.boot.web.permission.error.PermissionException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class NeedLoginFilter implements AuthorityFilter {

    @Override
    public void check(Method targetMethod, AuthorityFilterChain authorityFilterChain, PermissionService permissionService, BucService bucService) {

        NeedLogin annotation = targetMethod.getAnnotation(NeedLogin.class);
        if (annotation == null) {
            return;
        }

        boolean hasLogin = permissionService.hasLogin();

        if (!hasLogin) {
            throw new PermissionException("需要登录!");
        }

    }
}
