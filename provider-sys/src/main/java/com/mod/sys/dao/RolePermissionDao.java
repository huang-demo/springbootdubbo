package com.mod.sys.dao;

import com.mod.sys.entity.po.RolePermissionPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
public interface RolePermissionDao extends BaseMapper<RolePermissionPO> {

    /**
     * resetPermission
     * @param roleId
     */
    void resetPermission(@Param("roleId") Long roleId);
}
