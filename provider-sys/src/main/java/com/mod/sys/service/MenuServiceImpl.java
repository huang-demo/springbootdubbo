package com.mod.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mod.sys.dao.MenuDao;
import com.mod.sys.entity.po.MenuPO;
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
public class MenuServiceImpl extends ServiceImpl<MenuDao,MenuPO> implements IMenuService {

}