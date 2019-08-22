package com.zark.sbproject.boot.web.permission.aspect.filter;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;
import com.zark.sbproject.boot.web.permission.anno.NeedOneOfRoles;
import com.zark.sbproject.boot.web.permission.error.PermissionException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class NeedOneOfRolesFilter implements AuthorityFilter {

    @Override
    public void check(Method targetMethod, AuthorityFilterChain authorityFilterChain, PermissionService permissionService, BucService bucService) {

        NeedOneOfRoles annotation = targetMethod.getAnnotation(NeedOneOfRoles.class);
        if (annotation == null) {
            return;
        }

        String[] roles = annotation.roles();

        //TODO create by zark 2019-08-22 调用permissionService 判断

        boolean hasAnyRole = true;

        if (!hasAnyRole) {
            throw new PermissionException("no role permission. need any roles:" + Arrays.toString(roles));
        }

    }
}
