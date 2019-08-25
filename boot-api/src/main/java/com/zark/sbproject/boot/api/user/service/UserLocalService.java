package com.zark.sbproject.boot.api.user.service;

import com.zark.sbproject.boot.api.user.bo.UserBO;

import java.util.List;

/**
 * @author zark
 */
public interface UserLocalService {

    /**
     * 查看所有用户
     */
    List<UserBO> queryAll();

    /**
     * 保存
     */
    void insert(UserBO userBO);

    /**
     * 修改
     */
    void updateById(UserBO userBO);
}
