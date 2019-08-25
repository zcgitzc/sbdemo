package com.zark.sbproject.boot.service.user.service.impl;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.dao.user.entity.UserPO;
import com.zark.sbproject.boot.dao.user.entity.UserPOExample;
import com.zark.sbproject.boot.dao.user.mapper.UserPOMapperExt;
import com.zark.sbproject.boot.service.user.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.zark.sbproject.boot.api.user.service.UserLocalService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zark
 */
@Service
@Slf4j
public class UserLocalServiceImpl implements UserLocalService {

    @Resource
    private UserPOMapperExt userPOMapperExt;

    @Override
    public List<UserBO> queryAll() {
        List<UserPO> userPOS = userPOMapperExt.selectByExample(new UserPOExample());
        return userPOS.stream().map(UserUtil::poToBo).collect(Collectors.toList());
    }

    @Override
    public void insert(UserBO userBO) {
        UserPO userPO = UserUtil.boToPo(userBO);
        userPOMapperExt.insertSelective(userPO);
        userBO.setId(userPO.getId());
    }

    @Override
    public void updateById(UserBO userBO) {
        userPOMapperExt.updateByPrimaryKeySelective(UserUtil.boToPo(userBO));
    }
}
