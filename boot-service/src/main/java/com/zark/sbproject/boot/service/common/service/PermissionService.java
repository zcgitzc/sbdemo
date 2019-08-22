package com.zark.sbproject.boot.service.common.service;

public interface PermissionService {

    /**
     * 校验权限
     * @param permissionKey 权限Key
     * @return 是否有权限
     */
    boolean checkPermission(String permissionKey);

    /**
     * 是否登录
     * @return
     */
    boolean hasLogin();
}
