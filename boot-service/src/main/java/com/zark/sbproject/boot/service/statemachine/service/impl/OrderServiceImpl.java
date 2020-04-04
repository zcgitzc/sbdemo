package com.zark.sbproject.boot.service.statemachine.service.impl;

import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatus;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatusEvent;
import com.zark.sbproject.boot.service.statemachine.Order;
import com.zark.sbproject.boot.service.statemachine.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service("orderService")
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Resource
    private StateMachine<OrderStatus, OrderStatusEvent> orderStateMachine;

    @Resource
    private StateMachinePersister<OrderStatus, OrderStatusEvent, Order> orderStatePersister;

    private int id = 1;
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order create() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        order.setId(id++);
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Order pay(int id) {
        Order order = orders.get(id);
        log.info("threadName=" + Thread.currentThread().getName() + " 尝试支付 id=" + id);
        Message message = MessageBuilder.withPayload(OrderStatusEvent.PAYED).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            log.info("threadName=" + Thread.currentThread().getName() + " 支付失败, 状态异常 id=" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order deliver(int id) {
        Order order = orders.get(id);
        log.info("threadName=" + Thread.currentThread().getName() + " 尝试发货 id=" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusEvent.DELIVERY).setHeader("order", order).build(), orders.get(id))) {
            log.info("threadName=" + Thread.currentThread().getName() + " 发货失败，状态异常 id=" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order receive(int id) {
        Order order = orders.get(id);
        log.info("threadName=" + Thread.currentThread().getName() + " 尝试收货 id=" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusEvent.RECEIVED).setHeader("order", order).build(), orders.get(id))) {
            log.info("threadName=" + Thread.currentThread().getName() + " 收货失败，状态异常 id=" + id);
        }
        return orders.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }


    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param order
     * @return
     */
    private boolean sendEvent(Message<OrderStatusEvent> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            //尝试恢复状态机状态
            orderStatePersister.restore(orderStateMachine, order);
            result = orderStateMachine.sendEvent(message);
            //持久化状态机状态
            orderStatePersister.persist(orderStateMachine, order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}