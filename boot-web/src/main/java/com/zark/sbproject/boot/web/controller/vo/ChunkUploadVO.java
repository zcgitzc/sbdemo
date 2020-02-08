package com.zark.sbproject.boot.web.controller.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author zark
 * @description
 * @date 2020-02-08 18:31
 */
@Data
public class ChunkUploadVO {

    private String guid;

    private String id;

    private String name;

    private String type;

    private Date lastModifiedDate;

    private long size;

    private int chunks;

    private int chunk;
}
