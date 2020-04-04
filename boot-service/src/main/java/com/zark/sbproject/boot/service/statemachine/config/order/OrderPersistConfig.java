package com.zark.sbproject.boot.service.statemachine.config.order;

import com.zark.sbproject.boot.service.constants.OrderConstants;
import com.zark.sbproject.boot.service.statemachine.persist.OrderStatePersist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;

@Configuration
public class OrderPersistConfig {

    @Resource
    private OrderStatePersist orderStatePersist;

    @Bean(name = "orderStatePersister")
    public StateMachinePersister<OrderConstants.OrderStatus, OrderConstants.OrderStatusEvent, String> getPersister() {
        return new DefaultStateMachinePersister(orderStatePersist);
    }
}