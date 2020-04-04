package com.zark.sbproject.boot.service.statemachine;

import com.zark.sbproject.boot.service.constants.TaskConstants;
import lombok.Data;

/**
 * @author wb-zc189961
 * @date 2020-03-24
 */
@Data
public class Task {
    private Integer id;

    private TaskConstants.TaskStatus status;

}
