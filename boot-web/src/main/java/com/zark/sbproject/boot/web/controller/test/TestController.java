package com.zark.sbproject.boot.web.controller.test;

import com.zark.sbproject.boot.api.user.bo.UserBO;
import com.zark.sbproject.boot.api.user.service.UserLocalService;
import com.zark.sbproject.boot.service.common.bo.GraphInstanceBO;
import com.zark.sbproject.boot.service.common.bo.MessageDealBO;
import com.zark.sbproject.boot.service.common.constant.GraphStatus;
import com.zark.sbproject.boot.service.common.message.producer.ActiveMqMessageProducer;
import com.zark.sbproject.boot.service.common.service.LockLocalService;
import com.zark.sbproject.boot.service.common.service.MessageDealLocalService;
import com.zark.sbproject.boot.service.common.service.TransactionalService;
import lombok.Data;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;

@Data
@RestController
public class TestController {

    @Resource
    private ActiveMqMessageProducer activeMqMessageProducer;
    @Resource
    private MessageDealLocalService messageDealLocalService;
    @Resource
    private LockLocalService lockLocalService;
    @Resource
    private UserLocalService userLocalService;
    @Resource
    private TransactionalService transactionalService;

    private static final RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
            .handle(ArithmeticException.class)
            .withDelay(Duration.ofSeconds(3))
            .withMaxRetries(3);

    @GetMapping("sendQueue")
    public void sendQueueMessage(String message) {
        activeMqMessageProducer.sendMessageToQueue("i-queue", message);
    }

    @GetMapping("sendTopic")
    public void sendTopicMessage(String message) {
        activeMqMessageProducer.sendMessageToTopic("i-topic", message);
    }

// 通过 ActiveMqMessageListener 统一监听
//    @JmsListener(destination = "i-queue ", containerFactory = "queueListenerFactory")
//    public void receiveQueue(String message) {
//        System.out.println("receive queue message:" + message);
//
//    }
//
//    @JmsListener(destination = "i-topic ", containerFactory = "topicListenerFactory")
//    public void receiveTopic(String message) {
//        System.out.println("receive topic message:" + message);
//    }


    @GetMapping("getMessageDeal")
    public MessageDealBO getMessageDeal(String messageId) {
        MessageDealBO messageDealBO = messageDealLocalService.lockByDestination(messageId);
        return messageDealBO;
    }

    @GetMapping("exception")
    public void exception() {
        System.out.println(1 / 0);
    }

    @GetMapping("lock")
    public void lock() {
        boolean hasLock = lockLocalService.tryLock("TEST");
        if (hasLock) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                lockLocalService.release("TEST");
            }
        } else {
            System.out.println("get lock failure. please try later");
        }
    }

    @PostMapping("dalAop")
    public void dalAop(@RequestBody UserBO userBO) {
        transactionalService.executeWithNewTransactional(() -> {
            userLocalService.insert(userBO);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userBO.setUsername("username - update");
            userLocalService.updateById(userBO);
        });
    }

    @GetMapping("graph")
    public void graph() {
        GraphInstanceBO graphInstanceBO = new GraphInstanceBO(GraphStatus.INIT);
        GraphStatus nextStatus = graphInstanceBO.getStatus().getNextStatus((currStatus) -> {
            //TODO create by zark 2019-08-25 处理从 init->started 的逻辑
            userLocalService.queryAll();
            System.out.println("currStatus:" + currStatus);
        });
        System.out.println("nextStatus:" + nextStatus);
        graphInstanceBO.setStatus(nextStatus);

        System.out.println("--------");

        GraphStatus nextStatus1 = graphInstanceBO.getStatus().getNextStatus((currStatus) -> System.out.println("currStatus:" + currStatus));
        System.out.println("nextStatus1:" + nextStatus1);
        graphInstanceBO.setStatus(nextStatus1);

        System.out.println("--------");

        GraphStatus nextStatus2 = graphInstanceBO.getStatus().getNextStatus((currStatus) -> System.out.println("currStatus:" + currStatus));
        System.out.println("nextStatus2:" + nextStatus2);
        graphInstanceBO.setStatus(nextStatus2);
    }


    public static void main(String[] args) {
        Failsafe.with(retryPolicy).run(() -> {
            System.out.println("error:");
            System.out.println(1 / 0);
        });
    }
}
