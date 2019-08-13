package com.zark.sbproject.boot.biz.user.service;

import com.zark.sbproject.boot.dao.user.entity.UserPO;

import java.util.List;

/**
 * @author zark
 */
public interface UserLocalService {

    List<UserPO> queryAll();
}
