package com.zark.sbproject.boot.web.permission.aspect.filter;

/**
* @desc 
* @author zark
* @date 2019-08-22
*/
public enum FilterChainFactory {

    INSTANCE;


    public AuthorityFilterChain getFilterChain() {

        AuthorityFilterChain authorityFilterChain = new AuthorityFilterChain();

        NeedRolesFilter needRolesFilter = new NeedRolesFilter();
        NeedOneOfRolesFilter needOneOfRolesFilter = new NeedOneOfRolesFilter();
        NeedPermissionsFilter needPermissionsFilter = new NeedPermissionsFilter();
        NeedOneOfPermissionsFilter needOneOfPermissionsFilter = new NeedOneOfPermissionsFilter();
        NeedLoginFilter needLoginFilter = new NeedLoginFilter();


        authorityFilterChain.addFilter(needRolesFilter)
                .addFilter(needOneOfRolesFilter)
                .addFilter(needPermissionsFilter)
                .addFilter(needOneOfPermissionsFilter)
                .addFilter(needLoginFilter);


        return authorityFilterChain;
    }
}
