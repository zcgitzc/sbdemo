package com.zark.sbproject.boot.service.user.service.impl;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.dao.user.entity.UserPO;
import com.zark.sbproject.boot.dao.user.entity.UserPOExample;
import com.zark.sbproject.boot.dao.user.mapper.UserPOMapperExt;
import com.zark.sbproject.boot.service.user.util.UserUtil;
import org.springframework.stereotype.Service;
import com.zark.sbproject.boot.api.user.service.UserLocalService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zark
 */
@Service
public class UserLocalServiceImpl implements UserLocalService {

    @Resource
    private UserPOMapperExt userPOMapperExt;

    @Override
    public List<UserBO> queryAll() {
        List<UserPO> userPOS = userPOMapperExt.selectByExample(new UserPOExample());
        return userPOS.stream().map(UserUtil::poToBo).collect(Collectors.toList());
    }
}
