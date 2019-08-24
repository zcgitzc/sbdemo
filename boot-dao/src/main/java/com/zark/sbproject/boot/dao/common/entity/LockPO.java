package com.zark.sbproject.boot.dao.common.entity;

import java.util.Date;

/**
 * 分布式锁
 *
 * @author zark
 * @date   2019-08-24
 */
public class LockPO {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String modifier;

    /**
     * 
     */
    private String isDeleted;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModified;

    /**
     * 
     */
    private String creator;

    /**
     * ?Key
     */
    private String lockKey;

    /**
     * 失效时间
     */
    private Date expireTime;

    /**
     * 
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
    * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return modifier
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 
    * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * 
     * @return is_deleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 
    * @param isDeleted
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * 
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 
    * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 
    * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 
    * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ?Key
     * @return lock_key
     */
    public String getLockKey() {
        return lockKey;
    }

    /**
     * ?Key
    * @param lockKey
     */
    public void setLockKey(String lockKey) {
        this.lockKey = lockKey == null ? null : lockKey.trim();
    }

    /**
     * 失效时间
     * @return expire_time
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 失效时间
    * @param expireTime
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}