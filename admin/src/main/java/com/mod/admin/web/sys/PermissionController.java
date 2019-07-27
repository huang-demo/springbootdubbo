package com.mod.admin.web.sys;


import com.mod.common.core.Result;
import com.mod.sys.entity.dto.PermissionDTO;
import com.mod.sys.entity.dto.PermissionMenuDTO;
import com.mod.sys.service.IPermissionService;
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
 *  前端控制器
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-26
 */
@RestController
@RequestMapping("/sys/permission")
@Api(tags = "系统模块-权限配置")
public class PermissionController extends BaseController {

    @Reference
    private IPermissionService permissionService;

    @PostMapping("/saveOrUpdate")
    @ApiOperation("保存或者更新")
    public Result saveOrUpdate(@RequestBody PermissionDTO permissionDTO){
        return Result.success();
    }
    @PostMapping("/setMenu")
    @ApiOperation("设置权限-菜单")
    public Result setMenu(@RequestBody PermissionMenuDTO permissionMenuDTO){
        return Result.success();
    }
    @PostMapping("/resetMenu")
    @ApiOperation("重置权限")
    public Result resetMenu(@RequestBody PermissionMenuDTO dto){
        return Result.success();
    }
}
