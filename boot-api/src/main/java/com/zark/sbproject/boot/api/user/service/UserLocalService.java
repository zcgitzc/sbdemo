package com.zark.sbproject.boot.api.user.service;

import com.zark.sbproject.boot.api.user.bo.UserBO;

import java.util.List;

/**
 * @author zark
 */
public interface UserLocalService {

    /**
     * 查看所有用户
     * @return
     */
    List<UserBO> queryAll();
}
