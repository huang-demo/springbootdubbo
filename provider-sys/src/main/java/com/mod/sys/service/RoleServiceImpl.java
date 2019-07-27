package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.sys.dao.RoleDao;
import com.mod.sys.entity.dto.RolePageDTO;
import com.mod.sys.entity.po.RolePO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mod.sys.entity.vo.RoleVO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@Service(token = "sys")
public class RoleServiceImpl extends ServiceImpl<RoleDao,RolePO> implements IRoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public Page<RoleVO> queryPage(RolePageDTO dto){
        Page<RoleVO> page = new Page<>(dto.getPageIndex(),dto.getPageSize());
        List<RoleVO> list = roleDao.queryPage(page,dto);
        page.setRecords(list);
        return page;
    }
}
