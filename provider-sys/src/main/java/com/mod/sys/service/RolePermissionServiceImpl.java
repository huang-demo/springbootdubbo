package com.mod.sys.service;

import com.mod.sys.entity.po.RolePermissionPO;
import com.mod.sys.dao.RolePermissionDao;
import com.mod.sys.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionPO> implements IRolePermissionService {

}
