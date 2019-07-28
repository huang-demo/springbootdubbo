package com.mod.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.sys.dao.PermissionDao;
import com.mod.sys.entity.dto.PermissionDTO;
import com.mod.sys.entity.po.PermissionPO;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionPO> implements IPermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public Long insertOrUpdate(PermissionDTO permissionDTO) {

        if (permissionDTO.getPermissionId() == null) {
            PermissionPO po = new PermissionPO();
            po.setPermissionName(permissionDTO.getPermissionName());
            po.setParentId(permissionDTO.getPid());
            po.setPermissionCode(permissionDTO.getPermissionCode());
            permissionDao.insert(po);
            return po.getPermissionId();
        } else {
            PermissionPO po = new PermissionPO();
            po.setPermissionName(permissionDTO.getPermissionName());
            po.setParentId(permissionDTO.getPid());
            po.setPermissionCode(permissionDTO.getPermissionCode());
            permissionDao.update(po,new QueryWrapper<PermissionPO>().eq("permission_id",permissionDTO.getPermissionId()));
            return permissionDTO.getPermissionId();
        }
    }


}
