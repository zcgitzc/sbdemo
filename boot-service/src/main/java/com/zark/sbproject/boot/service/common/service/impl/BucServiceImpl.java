package com.zark.sbproject.boot.service.common.service.impl;

import com.zark.sbproject.boot.service.common.service.BucService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 当前用户操作
 * @author zark
 * @date 2019-08-22
 */
@Service
public class BucServiceImpl implements BucService {

    @Override
    public String getCurrUsername() {
        return "zark";
    }
}
