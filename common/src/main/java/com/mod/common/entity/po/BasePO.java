package com.mod.common.entity.po;


import lombok.Data;

import java.util.Date;

@Data
public class BasePO {
    private Long createUser;
    private Date createTime;
    private Long updateUser;
    private Date updateTime;
    private Long version;
}
