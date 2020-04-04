package com.zark.sbproject.boot.service.statemachine.config.order;

import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatus;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatusEvent;
import com.zark.sbproject.boot.service.statemachine.interceptor.OrderStateInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.LifecycleObjectSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderStateLifecycle extends LifecycleObjectSupport {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusEvent> orderStateMachine;

    @Override
    protected void onInit() throws Exception {
        log.info("com.example.demo.statemachine.config.order.OrderStateLifecycle.onInit");
        //往stateMachine加入拦截器PersistingStateChangeInterceptor
        orderStateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.addStateMachineInterceptor(new OrderStateInterceptor()));
    }

    @Override
    protected void doStart() {
        log.info("com.example.demo.statemachine.config.order.OrderStateLifecycle.doStart");
    }

    @Override
    protected void doStop() {
        log.info("com.example.demo.statemachine.config.order.OrderStateLifecycle.doStop");
    }

    @Override
    protected void doDestroy() {
        log.info("com.example.demo.statemachine.config.order.OrderStateLifecycle.doDestroy");
    }


}