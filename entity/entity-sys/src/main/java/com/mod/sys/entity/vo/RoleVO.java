package com.mod.sys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/24 17:41
 */
@Data
public class RoleVO implements Serializable{
    private Long roleId;
    private String roleName;
    private String roleCode;
    private Integer roleState;
    private String remark;
}
