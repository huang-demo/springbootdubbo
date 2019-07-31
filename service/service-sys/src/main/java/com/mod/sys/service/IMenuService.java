package com.mod.sys.service;

import com.mod.sys.entity.dto.MenuDTO;
import com.mod.sys.entity.dto.MenuQueryDTO;
import com.mod.sys.entity.po.MenuPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.sys.entity.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
public interface IMenuService extends IService<MenuPO> {

    /**
     * 保存/更新
     * @param menuDTO
     */
    void saveOrUpdate(MenuDTO menuDTO);

    /**
     * 校验名称是否重复
     * @param dto
     * @return
     */
    boolean checkMenuName(MenuDTO dto);

    /**
     * 菜单查询
     * @param queryDTO
     * @return
     */
    List<MenuVO> queryMenu(MenuQueryDTO queryDTO);

    /**
     * 左侧菜单
     * @param userId
     * @return
     */
    List<MenuVO> getMenu(Long userId);
}
