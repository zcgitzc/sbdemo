package com.zark.sbproject.boot.web.permission.aspect.filter;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;
import com.zark.sbproject.boot.web.permission.anno.NeedOneOfPermissions;
import com.zark.sbproject.boot.web.permission.error.PermissionException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class NeedOneOfPermissionsFilter implements AuthorityFilter {

    @Override
    public void check(Method targetMethod, AuthorityFilterChain authorityFilterChain, PermissionService permissionService, BucService bucService) {

        NeedOneOfPermissions annotation = targetMethod.getAnnotation(NeedOneOfPermissions.class);
        if (annotation == null) {
            return;
        }

        String[] permissions = annotation.permissions();

        //TODO create by zark 2019-08-22 调用permissionService判断
        boolean hasAnyPermission = true;

        if (!hasAnyPermission) {
            throw new PermissionException("no permission. need any permissions:" + Arrays.toString(permissions));
        }

    }
}
