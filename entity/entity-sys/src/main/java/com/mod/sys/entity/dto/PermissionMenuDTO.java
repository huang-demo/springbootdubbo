package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PermissionMenuDTO implements Serializable {
    private Long permissionId;
    @ApiModelProperty("操作类型 1，设置，2移除")
    private Integer opt;
    private List<Long> menuList;
}
