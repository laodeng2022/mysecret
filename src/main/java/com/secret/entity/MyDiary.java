package com.secret.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (MyDiary)实体类
 *
 * @author makejava
 * @since 2022-04-15 19:17:25
 */
@Data
public class MyDiary implements Serializable {
    private static final long serialVersionUID = -33098272126286180L;
    
    private Long id;
    
    private String content;
    
    private String title;
    
    private Long createdBy;
    
    private Date createdDate;
    
    private Long lastUpdatedBy;
    
    private Date lastUpdatedDate;

}

