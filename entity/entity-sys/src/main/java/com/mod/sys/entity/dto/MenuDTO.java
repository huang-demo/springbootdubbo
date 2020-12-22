package com.mod.sys.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/24 15:07
 */
@Data
public class MenuDTO implements Serializable{
    @ApiModelProperty("菜单id")
    private Long menuId;
    @ApiModelProperty("菜单名称")
    @NotNull(message = "名称不能为空")
    private String menuName;
    @ApiModelProperty("菜单类型 0目录,1菜单,2按钮")
    private Integer menuType;
    @ApiModelProperty("上级")
    private Long pid;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("状态 1启用 2禁用")
    private Integer menuState;
    @ApiModelProperty("url")
    @Size(max = 200, message = "url不要超出200")
    private String url;
}
