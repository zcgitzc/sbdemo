package com.zark.sbproject.boot.service.common.message.consumer.handlers;

import com.zark.sbproject.boot.service.common.message.consumer.MessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zark
 * @date 2019-08-19
 */
@Slf4j
@Component
public class ITopicMessageHandler implements MessageHandler<String> {

    @Override
    public void handleMessage(String messageBody) {
        System.out.println("ITopicMessageHandler start handle message");
    }

    @Override
    public void handle(String message) {

    }

    @Override
    public String getTopic() {
        return "i-topic";
    }
}
