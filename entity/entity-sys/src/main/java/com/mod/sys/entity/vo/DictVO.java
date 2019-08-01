package com.mod.sys.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class DictVO implements Serializable {
    @ApiModelProperty("主键")
    private Long dictId;
    @ApiModelProperty("code")
    private String code;
    @ApiModelProperty("名称")
    private String dictName;
    @ApiModelProperty("值")
    private String dictValue;
    @ApiModelProperty("状态")
    private Integer dictState;
    private Long pid;

}
