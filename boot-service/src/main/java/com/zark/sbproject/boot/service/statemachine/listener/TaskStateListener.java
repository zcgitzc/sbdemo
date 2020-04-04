package com.zark.sbproject.boot.service.statemachine.listener;

import com.zark.sbproject.boot.service.constants.TaskConstants;
import com.zark.sbproject.boot.service.constants.TaskConstants.TaskStatusEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@WithStateMachine(name = TaskConstants.TASK_STATE_MACHINE_ID)
public class TaskStateListener {

    @OnTransition(source = "SI", target = "S1")
    public boolean s1Event(Message<TaskStatusEvent> message) {
        log.info("task headers=" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "S1", target = "S2")
    public boolean s2Event(Message<TaskStatusEvent> message) {
        log.info("task headers=" + message.getHeaders().toString());
        return true;
    }

}