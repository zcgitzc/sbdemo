package com.zark.sbproject.boot.service.constants;

/**
 * @author wb-zc189961
 * @date 2020-03-26
 */
public interface TaskConstants {

    String TASK_STATE_MACHINE_ID = "taskStateMachineId";

    enum TaskStatus {

        SI,

        S1,

        S2;

    }


    enum TaskStatusEvent {

        S1_EVENT,

        S2_EVENT;
    }

}
