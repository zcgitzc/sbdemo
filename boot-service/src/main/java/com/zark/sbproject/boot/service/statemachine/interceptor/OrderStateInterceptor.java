package com.zark.sbproject.boot.service.statemachine.interceptor;

import com.zark.sbproject.boot.service.constants.OrderConstants;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatus;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

/**
 * @author wb-zc189961
 * @date 2020-03-25
 */
@Component
@Slf4j
public class OrderStateInterceptor extends StateMachineInterceptorAdapter<OrderStatus, OrderStatusEvent> {

    @Override
    public Message<OrderStatusEvent> preEvent(Message<OrderStatusEvent> message, StateMachine<OrderStatus, OrderStatusEvent> stateMachine) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.preEvent");
        return message;
    }

    @Override
    public void preStateChange(State<OrderStatus, OrderStatusEvent> state, Message<OrderStatusEvent> message, Transition<OrderStatus, OrderStatusEvent> transition, StateMachine<OrderStatus, OrderStatusEvent> stateMachine) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.preStateChange");

    }

    @Override
    public void postStateChange(State<OrderStatus, OrderStatusEvent> state, Message<OrderStatusEvent> message, Transition<OrderStatus, OrderStatusEvent> transition, StateMachine<OrderStatus, OrderStatusEvent> stateMachine) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.postStateChange");

    }

    @Override
    public StateContext<OrderStatus, OrderStatusEvent> preTransition(StateContext<OrderStatus, OrderStatusEvent> stateContext) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.preTransition");
        return stateContext;
    }

    @Override
    public StateContext<OrderStatus, OrderStatusEvent> postTransition(StateContext<OrderStatus, OrderStatusEvent> stateContext) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.postTransition");
        return stateContext;
    }

    @Override
    public Exception stateMachineError(StateMachine<OrderStatus, OrderStatusEvent> stateMachine, Exception e) {
        log.info("com.example.demo.statemachine.interceptor.OrderStateInterceptor.stateMachineError");
        return e;
    }
}
