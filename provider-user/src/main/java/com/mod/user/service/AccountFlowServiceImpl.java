package com.mod.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.mod.user.entity.po.AccountFlowPO;
import com.mod.user.dao.AccountFlowDao;
import com.mod.user.service.IAccountFlowService;
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
public class AccountFlowServiceImpl extends ServiceImpl<AccountFlowDao, AccountFlowPO> implements IAccountFlowService {

}
