package com.mod.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.entity.dto.RolePageDTO;
import com.mod.sys.entity.po.RolePO;
import com.mod.sys.entity.vo.RoleVO;
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
public interface RoleDao extends BaseMapper<RolePO> {

    /**
     * queryPage
     * @param page
     * @param dto
     * @return
     */
    List<RoleVO> queryPage(@Param("page") Page<RoleVO> page,@Param("dto") RolePageDTO dto);

    /**
     * getRoleUrl
     * @return
     */
    List<UrlRoleBO> getRoleUrl();

}
