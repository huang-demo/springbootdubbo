package com.mod.sys.service;

import com.mod.sys.entity.dto.PermissionMenuDTO;
import com.mod.sys.entity.po.PermissionMenuPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
public interface IPermissionMenuService extends IService<PermissionMenuPO> {

    /**
     * setMenu
     * @param permissionMenuDTO
     */
    void setMenu(PermissionMenuDTO permissionMenuDTO);

    /**
     * resetMenu
     * @param permissionId
     */
    void resetMenu(Long permissionId);
}
