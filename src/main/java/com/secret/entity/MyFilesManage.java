package com.secret.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (MyFilesManage)实体类
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
@Data
public class MyFilesManage implements Serializable {
    private static final long serialVersionUID = 893909008373913912L;

    private Long id;

    private Long sourceId;

    private String sourceType;

    private String fileName;

    private String fileUrl;

    private Long createdBy;

    private Date createdDate;

    private Long lastUpdatedBy;

    private Date lastUpdatedDate;

}

