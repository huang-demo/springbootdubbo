package com.mod.sys.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/24 15:33
 */
@Data
public class MenuVO implements Serializable{
    @ApiModelProperty("菜单id")
    private Long menuId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单类型 0目录,1菜单,2按钮，3链接")
    private Integer menuType;
    @ApiModelProperty("父级")
    private Long pid;

    private String url;
    @ApiModelProperty("状态 1启用 2禁用")
    private Integer menuState;
    @ApiModelProperty("子节点")
    private List<MenuVO> childern;
}
