package com.zark.sbproject.boot.web.permission.aspect.filter;

import com.zark.sbproject.boot.service.common.service.BucService;
import com.zark.sbproject.boot.service.common.service.PermissionService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
* @desc 
* @author zark
* @date 2019-08-22
*/
public class AuthorityFilterChain {

    private List<AuthorityFilter> authorityFilters = new ArrayList<>();

    private int index;

    public AuthorityFilterChain addFilter(AuthorityFilter authorityFilter) {

        this.authorityFilters.add(authorityFilter);
        return this;
    }

    public void doCheck(Method targetMethod, PermissionService permissionService, BucService bucService) {
        if (index == authorityFilters.size()) {
            return;
        }

        while (index != authorityFilters.size()) {
            authorityFilters.get(index++).check(targetMethod, this, permissionService, bucService);
        }

    }

}
