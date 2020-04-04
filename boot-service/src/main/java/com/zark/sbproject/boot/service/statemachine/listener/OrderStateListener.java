package com.zark.sbproject.boot.service.statemachine.listener;

import com.zark.sbproject.boot.service.constants.OrderConstants;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatus;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatusEvent;
import com.zark.sbproject.boot.service.statemachine.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@WithStateMachine(name = OrderConstants.ORDER_STATE_MACHINE_ID)
public class OrderStateListener/* extends StateMachineListenerAdapter<OrderStatus, OrderStatusEvent>*/ {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<OrderStatusEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_DELIVER);
        log.info("支付 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderStatusEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_RECEIVE);
        log.info("发货 headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<OrderStatusEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.FINISH);
        log.info("收货 headers=" + message.getHeaders().toString());
        return true;
    }

//    @Override
//    public void stateChanged(State<OrderStatus, OrderStatusEvent> state, State<OrderStatus, OrderStatusEvent> state1) {
//        log.info("状态改变：" + (state == null ? null : state.getId()) + " ->" + state1.getId());
//    }
//
//
//    @Override
//    public void eventNotAccepted(Message<OrderStatusEvent> event) {
//        System.err.println(event + " | this event is not accepted ");
//    }
}