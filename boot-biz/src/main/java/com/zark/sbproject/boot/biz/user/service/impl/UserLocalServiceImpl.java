package com.zark.sbproject.boot.biz.user.service.impl;

import com.zark.sbproject.boot.biz.user.service.UserLocalService;
import com.zark.sbproject.boot.dao.user.entity.UserPO;
import com.zark.sbproject.boot.dao.user.entity.UserPOExample;
import com.zark.sbproject.boot.dao.user.mapper.UserPOMapperExt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zark
 */
@Service
public class UserLocalServiceImpl implements UserLocalService {

    @Resource
    private UserPOMapperExt userPOMapperExt;

    @Override
    public List<UserPO> queryAll() {
        return userPOMapperExt.selectByExample(new UserPOExample());
    }
}
