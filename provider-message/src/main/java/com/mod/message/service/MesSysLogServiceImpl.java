package com.mod.message.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.message.dao.MesSysLogDao;
import com.mod.message.entity.po.MesSysLogPO;
import org.apache.dubbo.config.annotation.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-09-05
 */
@Service
public class MesSysLogServiceImpl extends ServiceImpl<MesSysLogDao,MesSysLogPO> implements IMesSysLogService{

}
