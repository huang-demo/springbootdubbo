package com.mod.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.user.dao.LoginLogDao;
import com.mod.user.entity.po.LoginLogPO;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-30
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogPO> implements ILoginLogService {

}
