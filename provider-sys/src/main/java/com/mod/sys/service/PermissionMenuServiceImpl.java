package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.common.exception.ValidateParamException;
import com.mod.sys.dao.PermissionMenuDao;
import com.mod.sys.entity.dto.PermissionMenuDTO;
import com.mod.sys.entity.po.PermissionMenuPO;
import com.mod.sys.entity.po.PermissionPO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
@Service
public class PermissionMenuServiceImpl extends ServiceImpl<PermissionMenuDao, PermissionMenuPO> implements IPermissionMenuService {

    @Autowired
    private PermissionMenuDao permissionMenuDao;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public void setMenu(PermissionMenuDTO permissionMenuDTO) {
        PermissionPO permission = permissionService.getById(permissionMenuDTO.getPermissionId());
        if (permission == null) {
            throw new ValidateParamException("权限不存在");
        }
        if (PermissionMenuDTO.Opt.ADD.getCode().equals(permissionMenuDTO.getOpt())) {
            List<PermissionMenuPO> list = new ArrayList<>();
            for (Long menuId : permissionMenuDTO.getMenuList()) {
                PermissionMenuPO cur = new PermissionMenuPO();
                cur.setMenuId(menuId);
                cur.setPermissionId(permissionMenuDTO.getPermissionId());
                list.add(cur);
            }
            if (list.size() > 0) {
                this.saveBatch(list, list.size());
            }
        } else {
           permissionMenuDao.removeMenu(permission.getPermissionId(),permissionMenuDTO.getMenuList());

        }
    }

    @Override
    public void resetMenu(Long permissionId) {
        permissionMenuDao.resetMenu(permissionId);
    }
}
