package com.zark.sbproject.boot.web.controller;

import com.zark.sbproject.boot.service.common.message.IJmsProducer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private IJmsProducer iJmsProducer;


    @GetMapping("sendQueue")
    public void sendQueueMessage(String message) {
        iJmsProducer.sendMessageToQueue("i-queue", message);
    }

    @GetMapping("sendTopic")
    public void sendTopicMessage(String message) {
        iJmsProducer.sendMessageToTopic("i-topic", message);
    }


    @JmsListener(destination = "i-queue ", containerFactory = "queueListenerFactory")
    public void receiveQueue(String message) {
        System.out.println("receive queue message:" + message);

    }

    @JmsListener(destination = "i-topic ", containerFactory = "topicListenerFactory")
    public void receiveTopic(String message) {
        System.out.println("receive topic message:" + message);
    }

}
