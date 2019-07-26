package com.mod.admin.web.sys;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.common.core.Result;
import com.mod.sys.entity.dto.RoleDTO;
import com.mod.sys.entity.dto.RolePageDTO;
import com.mod.sys.entity.po.RolePO;
import com.mod.sys.entity.vo.RoleVO;
import com.mod.sys.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.mod.common.web.BaseController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/sys/role")
@Api(tags = "系统管理-角色管理")
public class RoleController extends BaseController{
    @Reference
    private IRoleService roleService;

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存/更新")
    public Result saveOrUpdate(@RequestBody RoleDTO dto){
        RolePO po = new RolePO();
        po.setRoleId(dto.getRoleId());
        po.setRoleState(dto.getState());
        po.setRoleName(dto.getRoleName());
        roleService.saveOrUpdate(po);
        return success();
    }
    @PostMapping("queryPage")
    @ApiOperation(value = "列表查询",response = RoleVO.class)
    public Result queryPage(@RequestBody RolePageDTO dto){
        Page<RoleVO> page = roleService.queryPage(dto);
        return success(page);
    }

}
