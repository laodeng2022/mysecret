package com.secret.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (MySetting)实体类
 *
 * @author makejava
 * @since 2022-04-18 21:00:28
 */
@Data
public class MySetting implements Serializable {
    private static final long serialVersionUID = -78282064900101737L;

    private Long id;

    private Long userId;

    private String autoSnap;

    private Long createdBy;

    private Date createdDate;

    private Long lastUpdatedBy;

    private Date lastUpdatedDate;
}

