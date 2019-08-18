package com.zark.sbproject.boot.service.common.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author zark
 * @date 2019-08-18
 */
@Data
public class MessageDealBO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 是否删除
     */
    private String isDeleted;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    /**
     * 处理状态
     */
    private String dealStatus;

    /**
     * 消息处理开始时间
     */
    private Date dealStartTime;

    /**
     * 消息处理结束时间
     */
    private Date dealEndTime;

    /**
     * 处理失败原因
     */
    private String errorMessage;

    /**
     * 消息唯一标识
     */
    private String destination;

    /**
     * 消息ID
     */
    private String messageId;

    /**
     * 处理次数（重复消费）
     */
    private Integer dealCount;
}
