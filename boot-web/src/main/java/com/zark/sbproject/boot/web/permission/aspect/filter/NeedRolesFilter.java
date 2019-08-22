package com.zark.sbproject.boot.web.permission.aspect.filter;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;
import com.zark.sbproject.boot.web.permission.anno.NeedRoles;
import com.zark.sbproject.boot.web.permission.error.PermissionException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class NeedRolesFilter implements AuthorityFilter {

    @Override
    public void check(Method targetMethod, AuthorityFilterChain authorityFilterChain, PermissionService permissionService, BucService bucService) {

        NeedRoles annotation = targetMethod.getAnnotation(NeedRoles.class);
        if (annotation == null) {
            return;
        }

        String[] roles = annotation.roles();
        //TODO create by zark 2019-08-22 调用permissionService判断
        boolean hasAllRole = true;
        if (!hasAllRole) {
            throw new PermissionException("no role permission. need roles:" + Arrays.toString(roles));
        }

    }
}
