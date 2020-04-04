package com.zark.sbproject.boot.service.statemachine.persist;

import com.zark.sbproject.boot.service.constants.OrderConstants;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatus;
import com.zark.sbproject.boot.service.constants.OrderConstants.OrderStatusEvent;
import com.zark.sbproject.boot.service.statemachine.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

/**
 * @author wb-zc189961
 * @date 2020-03-25
 */
@Component
@Slf4j
public class OrderStatePersist implements StateMachinePersist<OrderStatus, OrderStatusEvent, Order> {

    @Override
    public void write(StateMachineContext<OrderStatus, OrderStatusEvent> stateMachineContext, Order order) throws Exception {
        log.info("write:" + order);
        // TODO: 2020/3/26 add to db
    }

    @Override
    public StateMachineContext<OrderStatus, OrderStatusEvent> read(Order order) throws Exception {

        return new DefaultStateMachineContext<>(order.getStatus(), null, null,
                null, null, OrderConstants.ORDER_STATE_MACHINE_ID);
    }
}
