package com.zark.sbproject.boot.dao.common.entity;

import java.util.Date;

/**
 * 消息处理情况
 *
 * @author zark
 * @date   2019-08-24
 */
public class MessageDealPO {
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

    /**
     * 主键
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
    * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 是否删除
     * @return is_deleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 是否删除
    * @param isDeleted
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * 创建时间
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 创建时间
    * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 修改时间
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 修改时间
    * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 创建者
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建者
    * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 修改者
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改者
    * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 处理状态
     * @return deal_status
     */
    public String getDealStatus() {
        return dealStatus;
    }

    /**
     * 处理状态
    * @param dealStatus
     */
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus == null ? null : dealStatus.trim();
    }

    /**
     * 消息处理开始时间
     * @return deal_start_time
     */
    public Date getDealStartTime() {
        return dealStartTime;
    }

    /**
     * 消息处理开始时间
    * @param dealStartTime
     */
    public void setDealStartTime(Date dealStartTime) {
        this.dealStartTime = dealStartTime;
    }

    /**
     * 消息处理结束时间
     * @return deal_end_time
     */
    public Date getDealEndTime() {
        return dealEndTime;
    }

    /**
     * 消息处理结束时间
    * @param dealEndTime
     */
    public void setDealEndTime(Date dealEndTime) {
        this.dealEndTime = dealEndTime;
    }

    /**
     * 处理失败原因
     * @return error_message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 处理失败原因
    * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    /**
     * 消息唯一标识
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 消息唯一标识
    * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination == null ? null : destination.trim();
    }

    /**
     * 消息ID
     * @return message_id
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * 消息ID
    * @param messageId
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    /**
     * 处理次数（重复消费）
     * @return deal_count
     */
    public Integer getDealCount() {
        return dealCount;
    }

    /**
     * 处理次数（重复消费）
    * @param dealCount
     */
    public void setDealCount(Integer dealCount) {
        this.dealCount = dealCount;
    }
}