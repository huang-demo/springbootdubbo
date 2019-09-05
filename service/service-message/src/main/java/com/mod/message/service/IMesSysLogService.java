package com.mod.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.message.entity.dto.MesSysLogDTO;
import com.mod.message.entity.po.MesSysLogPO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-09-05
 */
public interface IMesSysLogService extends IService<MesSysLogPO>{

    /**
     * 保存错误日志
     * @param dto
     */
    void saveErrLog(MesSysLogDTO dto);
}
