package com.mod.admin.web.sys;


import com.mod.common.core.Result;
import com.mod.sys.entity.dto.MenuDTO;
import com.mod.sys.entity.dto.MenuQueryDTO;
import com.mod.sys.entity.vo.MenuVO;
import com.mod.sys.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.mod.common.web.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-23
 */
@RestController
@RequestMapping("/sys/menu")
@Api(tags = "系统模块-菜单管理")
public class MenuController extends BaseController{
    @Reference
    private IMenuService menuService;

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者更新")
    public Result saveOrUpdate(@Valid @RequestBody MenuDTO menuDTO){
        menuService.saveOrUpdate(menuDTO);
        return success();
    }

    @PostMapping("/queryMenu")
    @ApiOperation(value = "查询菜单", response = MenuVO.class)
    public Result queryMenu(@RequestBody MenuQueryDTO dto){
        return Result.success(menuService.queryMenu(dto));
    }

    @PostMapping("/checkName")
    @ApiOperation(value = "检查菜单名称是否重复", response = Boolean.class)
    public Result checkName(@RequestBody MenuDTO menuDTO){
        return success(menuService.checkMenuName(menuDTO));
    }

}
