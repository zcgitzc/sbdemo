package com.zark.sbproject.boot.service.common.message.consumer;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zark
 * @date 2019-08-18 22:41:28
 */
public interface MessageHandler<T> {

    /**
     * 处理方法
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handleMessage(String messageBody);

    /**
     * 处理消息
     *
     * @param message
     */
    void handle(T message);

    /**
     * 获取topic
     *
     * @return
     */
    String getTopic();
}
