package com.zark.sbproject.boot.service.common.message.producer.impl;

import com.zark.sbproject.boot.service.common.message.producer.ActiveMqMessageProducer;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ActiveMqMessageProducerImpl implements ActiveMqMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessageToTopic(String destination, final String message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(destination), message);
        log.info("send topic message success. destination:{},message:{}", destination, message);
    }

    @Override
    public void sendMessageToQueue(String destination, final String message) {
        jmsTemplate.convertAndSend(new ActiveMQQueue(destination), message);
        log.info("send queue message success. destination:{},message:{}", destination, message);
    }
}