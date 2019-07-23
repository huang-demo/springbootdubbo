package com.mod.user.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserDTO implements Serializable {
    private Long userId;
    private String userName;
    private String password;
}
