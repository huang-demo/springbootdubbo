package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RolePermissionDTO implements Serializable {
    @ApiModelProperty("角色id")
    private Long roleId;
    @ApiModelProperty("权限集合")
    private List<Long> permissionList;
}
