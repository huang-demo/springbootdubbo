package com.mod.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.entity.dto.RolePageDTO;
import com.mod.sys.entity.po.RolePO;
import com.mod.sys.entity.vo.RoleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
public interface IRoleService extends IService<RolePO> {

    /**
     * queryPage
     * @param dto
     * @return
     */
    Page<RoleVO> queryPage(RolePageDTO dto);

    /**
     * path:【role1,role2】
     */
    void cacheRolePermission();

    /**
     * getRoleUrl
     * @return
     */
    List<UrlRoleBO> getRoleUrl();
}
