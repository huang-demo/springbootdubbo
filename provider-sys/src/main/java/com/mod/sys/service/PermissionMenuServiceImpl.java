package com.mod.sys.service;

import com.mod.sys.entity.po.PermissionMenuPO;
import com.mod.sys.dao.PermissionMenuDao;
import com.mod.sys.service.IPermissionMenuService;
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
public class PermissionMenuServiceImpl extends ServiceImpl<PermissionMenuDao, PermissionMenuPO> implements IPermissionMenuService {

}
