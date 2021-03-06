package com.mod.admin.web.user;

import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import com.mod.common.core.Result;
import com.mod.common.web.BaseController;
import com.mod.user.entity.dto.UserInfoIdDTO;
import com.mod.user.entity.dto.UserInfoPageDTO;
import com.mod.user.service.IUserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/22 11:43
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户中心-用户信息")
public class UserInfoController extends BaseController{

    @Reference
    private IUserInfoService userInfoService;



    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody UserInfoIdDTO dto){
        return Result.success(userInfoService.getUserInfoById(dto));
    }
    @PostMapping("/queryPage")
    public Result queryPage(@RequestBody UserInfoPageDTO dto){
        return Result.success(userInfoService.queryPage(dto));
    }
}
