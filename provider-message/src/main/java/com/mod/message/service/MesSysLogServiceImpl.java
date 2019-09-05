package com.mod.message.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.message.dao.MesSysLogDao;
import com.mod.message.entity.dto.MesSysLogDTO;
import com.mod.message.entity.po.MesSysLogPO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MesSysLogDao mesSysLogDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveErrLog(MesSysLogDTO dto){
        MesSysLogPO sysLogPO = new MesSysLogPO();
        sysLogPO.setMessage(dto.getMessage());
        sysLogPO.setClassName(dto.getClassName());
        sysLogPO.setRequestTime(dto.getRequestTime());
        MesSysLogPO.Level level = MesSysLogPO.Level.getLevel(dto.getLevel());
        sysLogPO.setLevel(level != null ? level.getCode() : 0);
        sysLogPO.setRequestTime(dto.getRequestTime());
        sysLogPO.setTraceId(dto.getTraceId());
        mesSysLogDao.insert(sysLogPO);
    }
}
