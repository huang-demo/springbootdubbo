package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/24 17:39
 */
@Data
public class RoleDTO implements Serializable{
    @ApiModelProperty("角色id")
    private Long roleId;
    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色状态 0默认1启用2禁用")
    private Integer state;
}
