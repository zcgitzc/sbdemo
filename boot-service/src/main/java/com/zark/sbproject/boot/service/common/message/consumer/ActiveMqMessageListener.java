package com.zark.sbproject.boot.service.common.message.consumer;

import com.zark.sbproject.boot.common.util.SpringContextUtil;
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
public class ActiveMqMessageListener implements MessageListener {


//    @Resource
//    private MessageDealLocalService messageDealLocalService;

    @Override
    @Transactional
    public void onMessage(Message message) {
        MessageDealLocalService messageDealLocalService = (MessageDealLocalService) SpringContextUtil.applicationContext.getBean("messageDealLocalServiceImpl");
        try {
            String messageBody = getMessageBody(message);
            //todo query 的怎么监听
            Destination destination = message.getJMSDestination();
            ActiveMQTopic activeMqTopic = (ActiveMQTopic) destination;

            String messageId = message.getJMSMessageID();

            String topic = activeMqTopic.getTopicName();

            log.info("Receive message, topic : {}, message type : {},  message id : {},messageBody : {}", topic, messageId, messageBody);

            Date receiveDate = new Date();

            MessageDealBO messageDealBO = messageDealLocalService.lockByMessageId(messageId);

            //消息重复消费
            if (messageDealBO != null && MessageConstants.Status.SUCCESS.name().equals(messageDealBO.getDealStatus())) {
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
        MessageDealLocalService messageDealLocalService = (MessageDealLocalService) SpringContextUtil.applicationContext.getBean("messageDealLocalServiceImpl");

        try {
            handleMessage(messageId, messageBody, topic);
            messageDealBO.setDealStatus(MessageConstants.Status.SUCCESS.name());
        } catch (Throwable e) {
            log.error("Handle message : " + messageId + " failure, for reason : " + e.getMessage(), e);
            messageDealBO.setDealStatus(MessageConstants.Status.FAILURE.name());
            messageDealBO.setErrorMessage(e.getMessage());
        } finally {
            messageDealBO.setDealEndTime(new Date());
            messageDealLocalService.updateById(messageDealBO);
        }
    }

    private void handleMessage(String messageId, String messageBody, String topic) {
        MessageHandler<?> messageHandler = getMessageHandler(topic);
        long current = System.currentTimeMillis();
        messageHandler.handleMessage(messageBody);
        log.info("Handle message success, topic : {},  message id : {}, time cost : {}",
                topic, messageId, System.currentTimeMillis() - current);
    }

    @SuppressWarnings("rawtypes")
    private MessageHandler<?> getMessageHandler(String topic) {
        Collection<MessageHandler> beans = SpringContextUtil.applicationContext.getBeansOfType(MessageHandler.class).values();
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
            // TODO 其他消息类型
        } else {
            throw new RuntimeException("UNSUPPORTED_MESSAGE_TYPE");
        }
        return null;
    }

}
