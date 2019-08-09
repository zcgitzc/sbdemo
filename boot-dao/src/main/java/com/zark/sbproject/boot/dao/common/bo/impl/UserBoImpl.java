package com.zark.sbproject.boot.dao.common.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.activemq.broker.region.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import com.zark.sbproject.boot.dao.common.bo.UserBo;
import com.zark.sbproject.boot.entity.common.mapper.UserDoMapperExt;
import com.zark.sbproject.boot.entity.common.module.UserDo;
import com.zark.sbproject.boot.entity.common.module.UserDoExample;

@Service
public class UserBoImpl implements UserBo {

	@Resource
	private UserDoMapperExt userDoMapperExt;
//	@Autowired
//	private JmsMessagingTemplate jmsMessagingTemplate;

	@Override
	public List<UserDo> queryAll() {
		return userDoMapperExt.selectByExample(new UserDoExample());
	}

//	@Override
//	public void sendMessage(Destination destination, String message) {
//		jmsMessagingTemplate.convertAndSend(destination.getName(), message);
//	}

	
//	@JmsListener(destination = "return-queue")
//    public void Message(String message){
//        System.out.println("Product收到:"+message);
//    }
}
