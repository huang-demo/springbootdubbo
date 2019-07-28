package com.mod.sys.dao;

import com.mod.sys.entity.po.PermissionMenuPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
public interface PermissionMenuDao extends BaseMapper<PermissionMenuPO> {

    /**
     * 移除关系
     * @param permissionId
     * @param menuList
     */
    void removeMenu(@Param("permissionId") Long permissionId, @Param("menuList") List<Long> menuList);

    /**
     * resetMenu
     * @param permissionId
     */
    void resetMenu(@Param("permissionId") Long permissionId);
}
