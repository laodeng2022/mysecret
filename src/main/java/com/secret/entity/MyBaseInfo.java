package com.secret.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (MyBaseInfo)实体类
 *
 * @author makejava
 * @since 2022-04-18 21:00:27
 */
@Data
public class MyBaseInfo implements Serializable {
    private static final long serialVersionUID = 208993197495756062L;

    private Long id;

    private String account;

    private String password;

    private String phone;

    private String email;

    private Long createdBy;

    private Date createdDate;

    private Long lastUpdatedBy;

    private Date lastUpdatedDate;

    private String vipLevel;

    private String orderId;

    private String oldpassword;

    private String newpassword;

    private String lockpassword;

    private String maskpassword;
}

