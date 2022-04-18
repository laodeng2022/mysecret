package com.secret.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (VipRights)实体类
 *
 * @author makejava
 * @since 2022-04-18 21:08:16
 */
@Data
public class VipRights implements Serializable {
    private static final long serialVersionUID = 233522077352541249L;
    
    private Long id;
    
    private String title;
    
    private String url;
    
    private String vipLevel;

}

