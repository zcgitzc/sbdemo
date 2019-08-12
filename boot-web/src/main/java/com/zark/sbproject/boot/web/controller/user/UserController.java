package com.zark.sbproject.boot.web.controller.user;

import com.zark.sbproject.boot.biz.user.service.UserLocalService;
import com.zark.sbproject.boot.dao.user.entity.UserPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserLocalService userLocalService;

    @GetMapping("queryAll")
    public List<UserPO> queryAll() {
        return userLocalService.queryAll();
    }
}
