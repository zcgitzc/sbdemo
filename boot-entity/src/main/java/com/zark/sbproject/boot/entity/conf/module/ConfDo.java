package com.zark.sbproject.boot.entity.conf.module;

import java.util.Date;

public class ConfDo {
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
     * é…ç½®
     *
     * @mbggenerated
     */
    private String config;

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
     * é…ç½®
     * @return config é…ç½®
     *
     * @mbggenerated
     */
    public String getConfig() {
        return config;
    }

    /**
     * é…ç½®
     * @param config é…ç½®
     *
     * @mbggenerated
     */
    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }
}