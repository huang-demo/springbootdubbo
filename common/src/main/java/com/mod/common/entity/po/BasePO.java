package com.mod.common.entity.po;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BasePO implements Serializable{
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Long version;
}
