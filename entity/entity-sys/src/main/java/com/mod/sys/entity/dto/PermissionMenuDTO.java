package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Data
public class PermissionMenuDTO implements Serializable {
    private Long permissionId;
    @ApiModelProperty("操作类型 1，设置，2移除")
    private Integer opt;
    private List<Long> menuList;

    @Getter
    public enum Opt {
        //
        ADD(1),
        REMOVE(2),
        ;
        private Integer code;

        Opt(Integer code) {
            this.code = code;
        }
    }
}
