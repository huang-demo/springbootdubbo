package com.mod.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.sys.entity.dto.DictQueryDTO;
import com.mod.sys.entity.po.DictPO;
import com.mod.sys.entity.vo.DictVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
public interface DictDao extends BaseMapper<DictPO> {

    /**
     * queryPage
     * @param page
     * @param dto
     * @return
     */
    List<DictVO> queryPage(@Param("page") Page<DictVO> page, @Param("dto") DictQueryDTO dto);
}
