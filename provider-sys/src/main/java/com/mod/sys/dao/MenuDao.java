package com.mod.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mod.sys.entity.dto.MenuQueryDTO;
import com.mod.sys.entity.po.MenuPO;
import com.mod.sys.entity.vo.MenuVO;
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
public interface MenuDao extends BaseMapper<MenuPO> {

    /**
     * queryMenu
     * @param queryDTO
     * @return
     */
    List<MenuVO> queryMenu(MenuQueryDTO queryDTO);

    /**
     *getMenuByPid
     * @param userId
     * @param pid
     * @return
     */
    List<MenuVO> getMenuByPid(@Param("userId") Long userId,@Param("pid") Long pid);
}
