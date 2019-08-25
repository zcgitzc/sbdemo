package com.zark.sbproject.boot.service.common.service;

import com.zark.sbproject.boot.service.common.constant.GraphStatus;

/**
 * @author zark
 * @description
 * @date 2019-08-25 18:53
 */
public interface StatusEventService {
    void getNextStatus(GraphStatus currStatus);

}
