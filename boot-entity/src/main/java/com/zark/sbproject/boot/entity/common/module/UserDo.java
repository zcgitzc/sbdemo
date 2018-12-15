package com.zark.sbproject.boot.entity.common.module;

import java.util.Date;

public class UserDo {
    /**
     * ä¸»é”®
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated
     */
    private String isDeleted;

    /**
     * åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated
     */
    private Date gmtCreate;

    /**
     * ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated
     */
    private Date gmtModified;

    /**
     * åˆ›å»ºè€…
     *
     * @mbggenerated
     */
    private String creator;

    /**
     * ä¿®æ”¹è€…
     *
     * @mbggenerated
     */
    private String modifier;

    /**
     * ç”¨æˆ·å
     *
     * @mbggenerated
     */
    private String username;

    /**
     * å¯†ç 
     *
     * @mbggenerated
     */
    private String password;

    /**
     * ä¸»é”®
     * @return id ä¸»é”®
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * ä¸»é”®
     * @param id ä¸»é”®
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * æ˜¯å¦åˆ é™¤
     * @return is_deleted æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * æ˜¯å¦åˆ é™¤
     * @param isDeleted æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * åˆ›å»ºæ—¶é—´
     * @return gmt_create åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * åˆ›å»ºæ—¶é—´
     * @param gmtCreate åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * ä¿®æ”¹æ—¶é—´
     * @return gmt_modified ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * ä¿®æ”¹æ—¶é—´
     * @param gmtModified ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * åˆ›å»ºè€…
     * @return creator åˆ›å»ºè€…
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * åˆ›å»ºè€…
     * @param creator åˆ›å»ºè€…
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ä¿®æ”¹è€…
     * @return modifier ä¿®æ”¹è€…
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * ä¿®æ”¹è€…
     * @param modifier ä¿®æ”¹è€…
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * ç”¨æˆ·å
     * @return username ç”¨æˆ·å
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * ç”¨æˆ·å
     * @param username ç”¨æˆ·å
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * å¯†ç 
     * @return password å¯†ç 
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * å¯†ç 
     * @param password å¯†ç 
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}