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
@TableName("sys_role_menu")
public class RoleMenuPO extends BasePO{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "role_menu_id", type = IdType.AUTO)
    private Long roleMenuId;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 是否删除
     */
    private Integer isDeleted;


}
