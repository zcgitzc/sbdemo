package com.zark.sbproject.boot.dao.common.bo;

import java.util.List;

import org.apache.activemq.broker.region.Destination;

import com.zark.sbproject.boot.entity.common.module.UserDo;

public interface UserBo {
	List<UserDo> queryAll();
	
    void sendMessage(Destination destination,String message);

}
