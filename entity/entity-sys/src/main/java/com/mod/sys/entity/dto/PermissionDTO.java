package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PermissionDTO implements Serializable {
    private Long permissionId;
    @ApiModelProperty("权限名称")
    private String permissionName;

}
