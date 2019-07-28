package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.sys.entity.dto.PermissionDTO;
import com.mod.sys.entity.po.PermissionPO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
public interface IPermissionService extends IService<PermissionPO> {

    /**
     * 保存或者更新
     * @param permissionDTO
     * @return
     */
    Long insertOrUpdate(PermissionDTO permissionDTO);

}
