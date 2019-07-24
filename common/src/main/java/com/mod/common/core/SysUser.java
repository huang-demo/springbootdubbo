package com.mod.common.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    private Long userId;
    private String openid;
    private String userName;
}
