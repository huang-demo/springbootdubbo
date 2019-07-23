package com.mod.sys.entity.po;

import com.mod.common.entity.po.BasePO;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class MenuPO extends BasePO{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 菜单状态
     */
    private Integer menuState;

    /**
     * 跳转连接
     */
    private String url;

    /**
     * 图标
     */
    private String icon;


}
