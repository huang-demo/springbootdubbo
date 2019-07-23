package com.mod.miniapp.web.user;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mod.common.annotation.UserLoginToken;
import com.mod.common.core.Result;
import com.mod.common.web.BaseController;
import com.mod.user.entity.dto.UserInfoPageDTO;
import com.mod.user.entity.vo.UserInfoVO;
import com.mod.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Mr.p
 * @since 2019-07-18
 */
@RestController
@RequestMapping("/sys/userInfo")
@Api(tags = {"用户信息"})
public class UserInfoController extends BaseController {
    @Reference
    private IUserInfoService userInfoService;

    @PostMapping("/queryPage")
    @ApiOperation(value = "列表查询", response = UserInfoVO.class)
//    @UserLoginToken
    public Result queryPage(@RequestBody UserInfoPageDTO dto){
        Page<UserInfoVO> page = userInfoService.queryPage(dto);
        return success(page);
    }

}
