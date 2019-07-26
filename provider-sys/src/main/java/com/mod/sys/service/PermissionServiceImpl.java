package com.mod.sys.service;

import com.mod.sys.entity.po.PermissionPO;
import com.mod.sys.dao.PermissionDao;
import com.mod.sys.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, PermissionPO> implements IPermissionService {

}
