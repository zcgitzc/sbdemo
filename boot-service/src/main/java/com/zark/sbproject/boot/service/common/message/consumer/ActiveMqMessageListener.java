package com.zark.sbproject.boot.service.common.message.consumer;

import com.zark.sbproject.boot.service.common.bo.MessageDealBO;
import com.zark.sbproject.boot.service.common.constant.MessageConstants;
import com.zark.sbproject.boot.service.common.service.MessageDealLocalService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author zark
 * @date 2019-08-18 02:20:50
 */
@Slf4j
@Component
public class ActiveMqMessageListener implements MessageListener, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private MessageDealLocalService messageDealLocalService;


    @Override
    @Transactional
    public void onMessage(Message message) {
        try {
            String messageBody = getMessageBody(message);
            //TODO create by zark 2019-08-19 query 的怎么监听、消息分组GroupId
            Destination destination = message.getJMSDestination();
            ActiveMQTopic activeMqTopic = (ActiveMQTopic) destination;

            String messageId = message.getJMSMessageID();

            String topic = activeMqTopic.getTopicName();

            log.info("Receive message, topic : {}, message id : {},messageBody : {}", topic, messageId, messageBody);

            Date receiveDate = new Date();

            MessageDealBO messageDealBO = messageDealLocalService.lockByDestination(topic);

            //消息重复消费
            if (messageDealBO != null && MessageConstants.Status.SUCCESS.name().equals(messageDealBO.getDealStatus())) {
                //TODO create by zark 2019-08-27 其实表结构没有弄好，不能记录每次的执行情况，只知道最后的状态（demo就算了，按这个写）
                messageDealBO.setDealStartTime(receiveDate);
                messageDealBO.setDealEndTime(receiveDate);
                messageDealBO.setDealCount(messageDealBO.getDealCount() + 1);
                messageDealLocalService.updateById(messageDealBO);
                return;
            }

            //消费失败后重试
            if (messageDealBO != null && MessageConstants.Status.FAILURE.name().equals(messageDealBO.getDealStatus())) {
                messageDealBO.setDealStartTime(receiveDate);
                messageDealBO.setDealCount(messageDealBO.getDealCount() + 1);
                messageDealLocalService.updateById(messageDealBO);

                handleMessage(messageId, messageBody, topic, messageDealBO);
            }

            //第一次消费
            if (messageDealBO == null) {
                MessageDealBO paramMessageDealBO = new MessageDealBO();

                paramMessageDealBO.setDealStartTime(receiveDate);
                paramMessageDealBO.setDealCount(1);
                paramMessageDealBO.setMessageId(messageId);
                paramMessageDealBO.setDestination(topic);

                messageDealLocalService.insert(paramMessageDealBO);
                handleMessage(messageId, messageBody, topic, paramMessageDealBO);

            }

        } catch (JMSException e) {
            log.error("get destination error." + e.getErrorCode() + ":" + e.getMessage());
        }
    }

    private void handleMessage(String messageId, String messageBody, String topic, MessageDealBO messageDealBO) {
        try {
            long current = System.currentTimeMillis();
            handleMessage(messageId, messageBody, topic);
            log.info("Handle message success, topic : {},  message id : {}, time cost : {}",
                    topic, messageId, System.currentTimeMillis() - current);
            messageDealBO.setDealStatus(MessageConstants.Status.SUCCESS.name());
        } catch (Throwable e) {
            log.error("Handle message failure, messageId: " + messageId + " for reason : " + e.getMessage(), e);
            messageDealBO.setDealStatus(MessageConstants.Status.FAILURE.name());
            messageDealBO.setErrorMessage(e.getMessage());
        } finally {
            messageDealBO.setDealEndTime(new Date());
            messageDealLocalService.updateById(messageDealBO);
        }
    }

    private void handleMessage(String messageId, String messageBody, String topic) {
        MessageHandler<?> messageHandler = getMessageHandler(topic);
        messageHandler.handleMessage(messageBody);
    }

    private MessageHandler<?> getMessageHandler(String topic) {
        Collection<MessageHandler> beans = applicationContext.getBeansOfType(MessageHandler.class).values();
        for (MessageHandler handler : beans) {
            if (handler.getTopic().equals(topic)) {
                return handler;
            }
        }
        throw new RuntimeException("can't find MessageHandler. topic:" + topic);
    }


    private String getMessageBody(Message message) throws JMSException {

        //javax.jms.BytesMessage
        //javax.jms.MapMessage
        //javax.jms.ObjectMessage
        //javax.jms.StreamMessage
        //javax.jms.TextMessage

        if (message instanceof TextMessage) {
            return ((TextMessage) message).getText();
        } else if (message instanceof BytesMessage) {
            //TODO create by zark 2019-08-19 其他消息类型
        } else {
            throw new RuntimeException("UNSUPPORTED_MESSAGE_TYPE");
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
