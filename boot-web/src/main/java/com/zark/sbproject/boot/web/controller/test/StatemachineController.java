package com.zark.sbproject.boot.web.controller.test;

import com.zark.sbproject.boot.service.constants.TaskConstants;
import com.zark.sbproject.boot.service.statemachine.Order;
import com.zark.sbproject.boot.service.statemachine.service.OrderService;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zark
 * @description
 * @date 2020-04-04 14:28
 */
@RestController
@RequestMapping("statemachine")
public class StatemachineController {

    @Resource
    private OrderService orderService;
    @Resource
    private StateMachine<TaskConstants.TaskStatus,TaskConstants.TaskStatusEvent> taskStateMachine;


    @RequestMapping("/1")
    public String test1() {

        Order order = orderService.create();

        Integer id = order.getId();

        orderService.pay(id);

        orderService.deliver(id);

        orderService.receive(id);

        return id.toString();
    }

    @RequestMapping("/2")
    public String test2(){
        taskStateMachine.start();

        taskStateMachine.sendEvent(TaskConstants.TaskStatusEvent.S1_EVENT);

        taskStateMachine.sendEvent(TaskConstants.TaskStatusEvent.S2_EVENT);

        return "simple statemachine";
    }

}
