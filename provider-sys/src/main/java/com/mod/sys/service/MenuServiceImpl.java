package com.mod.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mod.common.constant.ExceptionCode;
import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
import com.mod.sys.dao.MenuDao;
import com.mod.sys.entity.dto.MenuDTO;
import com.mod.sys.entity.dto.MenuQueryDTO;
import com.mod.sys.entity.po.MenuPO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.sys.entity.vo.MenuVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,MenuPO> implements IMenuService{

    @Autowired
    private MenuDao menuDao;

    @Override
    public void saveOrUpdate(MenuDTO menuDTO){
        boolean checkMenuName = checkMenuName(menuDTO);
        if(!checkMenuName){
            throw new GlobalException("菜单名称已被使用!");
        }
        if(menuDTO.getMenuId() != null){
            updateMenu(menuDTO);
        }else{
            saveMenu(menuDTO);
        }

    }

    private void saveMenu(MenuDTO menuDTO){
        MenuPO po = new MenuPO();
        po.setMenuName(menuDTO.getMenuName());
        po.setMenuState(menuDTO.getMenuState());
        po.setParentId(menuDTO.getPid());
        po.setMenuType(menuDTO.getMenuType());
        po.setIcon(menuDTO.getIcon());
        po.setUrl(menuDTO.getUrl());
        menuDao.insert(po);
    }

    private void updateMenu(MenuDTO menuDTO){
        MenuPO menuPO = menuDao.selectById(menuDTO.getMenuId());
        if(menuPO == null){
            throw new GlobalException(ExceptionCode.DATA_NOT_EXIST);
        }
        menuPO.setMenuName(menuDTO.getMenuName());
        menuPO.setMenuState(menuDTO.getMenuState());
        menuPO.setMenuType(menuDTO.getMenuType());
        menuPO.setIcon(menuDTO.getIcon());
        menuPO.setUrl(menuDTO.getUrl());
        menuDao.updateById(menuPO);

    }

    @Override
    public boolean checkMenuName(MenuDTO dto){
        if(dto.getMenuId() != null){
            MenuPO menuPO = menuDao.selectById(dto.getMenuId());
            //自己改自己
            if(menuPO != null && menuPO.getMenuName().equals(dto.getMenuName())){
                return true;
            }
        }
        //同一级菜单名称不允许重复
        QueryWrapper<MenuPO> query = new QueryWrapper();
        query.eq("menu_name",dto.getMenuName());
        query.eq("parent_id",dto.getPid());
        Integer count = menuDao.selectCount(query);
        return count == null || count == 0;
    }

    @Override
    public List<MenuVO> queryMenu(MenuQueryDTO queryDTO){
        List<MenuVO> list = menuDao.queryMenu(queryDTO);

        if(SysConstant.YES.equals(queryDTO.getWithChildern())){
            list.forEach(vo -> setChildern(vo));
        }
        return list;
    }

    private void setChildern(MenuVO menuVO){
        if(menuVO == null){
            return;
        }
        MenuQueryDTO query = new MenuQueryDTO();
        query.setPid(menuVO.getMenuId());
        List<MenuVO> list = menuDao.queryMenu(query);
        menuVO.setChildern(list);
        for(MenuVO vo: list){
            setChildern(vo);
        }
    }

    @Override
    public List<MenuVO> getMenu(Long userId){
        List<MenuVO> list = menuDao.getMenuByPid(userId,0L);
        for(MenuVO menuVO: list){
            setChldernMenu(menuVO,userId);
        }
        return list;
    }

    private void setChldernMenu(MenuVO menu,Long userId){
        if(menu == null){
            return;
        }
        List<MenuVO> list = menuDao.getMenuByPid(userId,menu.getMenuId());
        for(MenuVO cur: list){
            setChldernMenu(cur,userId);
        }
        menu.setChildern(list);
    }
}
