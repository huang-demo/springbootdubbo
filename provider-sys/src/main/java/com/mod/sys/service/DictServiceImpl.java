package com.mod.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mod.sys.dao.DictDao;
import com.mod.sys.entity.dto.DictDTO;
import com.mod.sys.entity.po.DictPO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, DictPO> implements IDictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public Long insertOrUpdate(DictDTO dto) {

        Long dictId = null;

        if (dto.getDictId() == null) {
            DictPO parent = dictDao.selectById(dto.getPid());
            DictPO dictPO = new DictPO();
            dictPO.setDictName(dto.getName());
            dictPO.setDictValue(dto.getValue());
            dictPO.setDictState(dto.getState());
            dictPO.setCode(dto.getCode());
            dictPO.setParentId(parent != null ? parent.getDictId() : 0L);
            dictDao.insert(dictPO);
            dictId = dictPO.getDictId();
        } else {
            DictPO dictPO = new DictPO();
            dictPO.setDictName(dto.getName());
            dictPO.setDictValue(dto.getValue());
            dictPO.setDictState(dto.getState());
            dictPO.setCode(dto.getCode());
            dictId = dto.getDictId();
            dictDao.update(dictPO, new QueryWrapper<DictPO>().eq("dict_id", dto.getDictId()));
        }
        return dictId;
    }
}
