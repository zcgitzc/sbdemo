package com.zark.sbproject.boot.web.controller.user;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.api.user.service.UserLocalService;
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
    public List<UserBO> queryAll() {
        return userLocalService.queryAll();
    }
}
