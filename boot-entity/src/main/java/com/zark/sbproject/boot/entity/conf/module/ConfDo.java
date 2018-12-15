package com.zark.sbproject.boot.entity.conf.module;

import java.util.Date;

public class ConfDo {
    /**
     * ä¸»é”®
     *
     * @mbggenerated 2018-12-15
     */
    private Long id;

    /**
     * æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated 2018-12-15
     */
    private String isDeleted;

    /**
     * åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    private Date gmtCreate;

    /**
     * ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    private Date gmtModified;

    /**
     * åˆ›å»ºè€…
     *
     * @mbggenerated 2018-12-15
     */
    private String creator;

    /**
     * ä¿®æ”¹è€…
     *
     * @mbggenerated 2018-12-15
     */
    private String modifier;

    /**
     * é…ç½®
     *
     * @mbggenerated 2018-12-15
     */
    private String config;

    /**
     * ä¸»é”®
     * @return id ä¸»é”®
     *
     * @mbggenerated 2018-12-15
     */
    public Long getId() {
        return id;
    }

    /**
     * ä¸»é”®
     * @param id ä¸»é”®
     *
     * @mbggenerated 2018-12-15
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * æ˜¯å¦åˆ é™¤
     * @return is_deleted æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated 2018-12-15
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * æ˜¯å¦åˆ é™¤
     * @param isDeleted æ˜¯å¦åˆ é™¤
     *
     * @mbggenerated 2018-12-15
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    /**
     * åˆ›å»ºæ—¶é—´
     * @return gmt_create åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * åˆ›å»ºæ—¶é—´
     * @param gmtCreate åˆ›å»ºæ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * ä¿®æ”¹æ—¶é—´
     * @return gmt_modified ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * ä¿®æ”¹æ—¶é—´
     * @param gmtModified ä¿®æ”¹æ—¶é—´
     *
     * @mbggenerated 2018-12-15
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * åˆ›å»ºè€…
     * @return creator åˆ›å»ºè€…
     *
     * @mbggenerated 2018-12-15
     */
    public String getCreator() {
        return creator;
    }

    /**
     * åˆ›å»ºè€…
     * @param creator åˆ›å»ºè€…
     *
     * @mbggenerated 2018-12-15
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * ä¿®æ”¹è€…
     * @return modifier ä¿®æ”¹è€…
     *
     * @mbggenerated 2018-12-15
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * ä¿®æ”¹è€…
     * @param modifier ä¿®æ”¹è€…
     *
     * @mbggenerated 2018-12-15
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * é…ç½®
     * @return config é…ç½®
     *
     * @mbggenerated 2018-12-15
     */
    public String getConfig() {
        return config;
    }

    /**
     * é…ç½®
     * @param config é…ç½®
     *
     * @mbggenerated 2018-12-15
     */
    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }
}