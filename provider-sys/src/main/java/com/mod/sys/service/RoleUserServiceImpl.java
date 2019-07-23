package com.mod.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mod.sys.dao.RoleUserDao;
import com.mod.sys.entity.po.RoleUserPO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserDao,RoleUserPO> implements IRoleUserService {

}
