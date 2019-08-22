package com.zark.sbproject.boot.web.controller;

import com.zark.sbproject.boot.service.common.bo.MessageDealBO;
import com.zark.sbproject.boot.service.common.message.producer.ActiveMqMessageProducer;
import com.zark.sbproject.boot.service.common.service.MessageDealLocalService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private ActiveMqMessageProducer activeMqMessageProducer;
    @Resource
    private MessageDealLocalService messageDealLocalService;


    @GetMapping("sendQueue")
    public void sendQueueMessage(String message) {
        activeMqMessageProducer.sendMessageToQueue("i-queue", message);
    }

    @GetMapping("sendTopic")
    public void sendTopicMessage(String message) {
        activeMqMessageProducer.sendMessageToTopic("i-topic", message);
    }


    @JmsListener(destination = "i-queue ", containerFactory = "queueListenerFactory")
    public void receiveQueue(String message) {
        System.out.println("receive queue message:" + message);

    }

    @JmsListener(destination = "i-topic ", containerFactory = "topicListenerFactory")
    public void receiveTopic(String message) {
        System.out.println("receive topic message:" + message);
    }


    @GetMapping("getMessageDeal")
    public MessageDealBO getMessageDeal(String messageId) {
        MessageDealBO messageDealBO = messageDealLocalService.lockByMessageId(messageId);
        return messageDealBO;
    }

    @GetMapping("exception")
    public void exception() {
        System.out.println(1 / 0);
    }
}
