package com.zark.sbproject.boot.web.controller.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zark.sbproject.boot.dao.common.bo.UserBo;
import com.zark.sbproject.boot.entity.common.module.UserDo;

@RestController
@RequestMapping("user")
public class UserController {
	@Resource
	private UserBo userBo;

	@RequestMapping("queryAll")
	public List<UserDo> queryAll(){
		return userBo.queryAll();
	}
}
