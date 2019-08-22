package com.zark.sbproject.boot.service.common.service.impl;

import com.zark.sbproject.boot.service.common.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @desc 权限处理类
 * @author zark
 * @date 2019-08-22
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasLogin() {
        return false;
    }

    @Override
    public boolean checkPermission(String permissionKey) {
        return true;
    }
}
