package com.zark.sbproject.boot.dao.common.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zark.sbproject.boot.dao.common.bo.UserBo;
import com.zark.sbproject.boot.entity.common.mapper.UserDoMapperExt;
import com.zark.sbproject.boot.entity.common.module.UserDo;
import com.zark.sbproject.boot.entity.common.module.UserDoExample;

@Service
public class UserBoImpl implements UserBo {

	@Resource
	private UserDoMapperExt userDoMapperExt;

	@Override
	public List<UserDo> queryAll() {
		return userDoMapperExt.selectByExample(new UserDoExample());
	}

}
