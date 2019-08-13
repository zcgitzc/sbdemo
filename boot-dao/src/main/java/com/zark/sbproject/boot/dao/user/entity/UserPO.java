package com.zark.sbproject.boot.dao.user.entity;

import java.util.Date;

/**
 * 用户表
 *
 * @author zark
 * @date   2019-08-12
 */
public class UserPO {
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
     * 用户名
     */
    private String username;

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
     * 用户名
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
    * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}