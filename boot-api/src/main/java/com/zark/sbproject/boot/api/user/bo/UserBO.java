package com.zark.sbproject.boot.api.user.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author zark
 * @date 2019-08-12
 */
@Data
public class UserBO {
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
}
