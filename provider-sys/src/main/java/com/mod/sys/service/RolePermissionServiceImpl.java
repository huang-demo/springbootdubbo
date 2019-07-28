package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.sys.dao.RolePermissionDao;
import com.mod.sys.entity.dto.RolePermissionDTO;
import com.mod.sys.entity.po.RolePermissionPO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermissionPO> implements IRolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void setPermission(RolePermissionDTO dto) {
        rolePermissionDao.resetPermission(dto.getRoleId());
        for (Long permissionId : dto.getPermissionList()) {
            RolePermissionPO cur = new RolePermissionPO();
            cur.setPermissionId(permissionId);
            cur.setRoleId(dto.getRoleId());
            rolePermissionDao.insert(cur);
        }
    }
}
