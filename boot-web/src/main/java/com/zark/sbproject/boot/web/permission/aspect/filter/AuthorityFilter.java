package com.zark.sbproject.boot.web.permission.aspect.filter;


import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;

import java.lang.reflect.Method;

/**
* @desc 
* @author zark
* @date 2019-08-22
*/
public interface AuthorityFilter {

    void check(Method targetMethod, AuthorityFilterChain authorityFilterChain, PermissionService permissionService, BucService bucService);
}
