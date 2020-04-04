package com.zark.sbproject.boot.service.statemachine.config.task;

import com.zark.sbproject.boot.service.constants.TaskConstants;
import com.zark.sbproject.boot.service.constants.TaskConstants.TaskStatus;
import com.zark.sbproject.boot.service.constants.TaskConstants.TaskStatusEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;


/**
 * @author wb-zc189961
 * @date 2020-03-24
 */
@Configuration
@EnableStateMachine(name = "taskStateMachine")
public class TaskStateMachineConfig extends EnumStateMachineConfigurerAdapter<TaskStatus, TaskStatusEvent> {
    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<TaskStatus, TaskStatusEvent> states) throws Exception {
        states
                .withStates()
                .initial(TaskStatus.SI)
                .states(EnumSet.allOf(TaskStatus.class));
    }

    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<TaskStatus, TaskStatusEvent> transitions) throws Exception {
        transitions
                .withExternal().source(TaskStatus.SI).target(TaskStatus.S1).event(TaskStatusEvent.S1_EVENT)
                .and()
                .withExternal().source(TaskStatus.S1).target(TaskStatus.S2).event(TaskStatusEvent.S2_EVENT);
    }


    /**
     * 添加listener / 自动开启
     *
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<TaskStatus, TaskStatusEvent> config)
            throws Exception {
        config
                .withConfiguration()
                .machineId(TaskConstants.TASK_STATE_MACHINE_ID);
    }

}
