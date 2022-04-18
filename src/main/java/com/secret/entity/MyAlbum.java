package com.secret.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (MyAlbum)实体类
 *
 * @author makejava
 * @since 2022-04-15 23:16:50
 */
@Data
public class MyAlbum implements Serializable {
    private static final long serialVersionUID = -74172808992046054L;
    
    private Long id;
    
    private String title;
    
    private Long createdBy;
    
    private Date createdDate;
    
    private Long lastUpdatedBy;
    
    private Date lastUpdatedDate;
}

