package com.zark.sbproject.boot.biz.common.message;

/**
 * 消息提供者接口
 * @author zark
 * @date 2019-08-15 22:40:07
 */
public interface IJmsProducer {

    /**
     * 发送消息（发布-订阅模式）
     * @param destination
     * @param message
     */
    void sendMessageToTopic(String destination, String message);

    /**
     * 发送消息（点对点）
     * @param destination
     * @param message
     */
    void sendMessageToQueue(String destination, String message);
}
