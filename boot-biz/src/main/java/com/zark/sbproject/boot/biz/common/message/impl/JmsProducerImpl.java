package com.zark.sbproject.boot.biz.common.message.impl;

import javax.annotation.Resource;
import com.zark.sbproject.boot.biz.common.message.IJmsProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author zark
 * @date 2019-08-15 22:41:08
 */
@Service
public class JmsProducerImpl implements IJmsProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToTopic(String destination, final String message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(destination), message);
    }

    @Override
    public void sendMessageToQueue(String destination, final String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue(destination), message);
    }
}