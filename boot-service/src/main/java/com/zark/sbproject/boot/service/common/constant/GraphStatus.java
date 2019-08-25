package com.zark.sbproject.boot.service.common.constant;

import com.zark.sbproject.boot.service.common.service.StatusEventService;
import lombok.Getter;

/**
 * @author zark
 * @description
 * @date 2019-08-25 18:47
 */
public enum GraphStatus {

    INIT("初始化") {
        @Override
        public GraphStatus getNextStatus(StatusEventService statusEventService) {
            statusEventService.getNextStatus(this.INIT);
            return this.STARTED;
        }
    },

    STARTED("已启动") {
        @Override
        public GraphStatus getNextStatus(StatusEventService statusEventService) {
            statusEventService.getNextStatus(this.STARTED);
            return this.SUSPEND;
        }
    },

    SUSPEND("挂起") {
        @Override
        public GraphStatus getNextStatus(StatusEventService statusEventService) {
            statusEventService.getNextStatus(this.SUSPEND);
            return this.FINISHED;
        }
    },

    FINISHED("已完成") {
        @Override
        public GraphStatus getNextStatus(StatusEventService statusEventService) {
            statusEventService.getNextStatus(this.FINISHED);
            return null;
        }
    };

    GraphStatus(String value) {
        this.value = value;
    }

    @Getter
    private String value;

    public abstract GraphStatus getNextStatus(StatusEventService statusEventService);

}

