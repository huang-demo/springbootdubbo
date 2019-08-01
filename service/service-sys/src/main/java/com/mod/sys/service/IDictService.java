package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.sys.entity.dto.DictDTO;
import com.mod.sys.entity.dto.DictQueryDTO;
import com.mod.sys.entity.po.DictPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.sys.entity.vo.DictVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
public interface IDictService extends IService<DictPO> {

    /**
     * 插入或者更新
     * @param dto
     * @return
     */
    Long insertOrUpdate(DictDTO dto);

    /**
     * queryPage
     * @param dto
     * @return
     */
    Page<DictVO> queryPage(DictQueryDTO dto);
}
