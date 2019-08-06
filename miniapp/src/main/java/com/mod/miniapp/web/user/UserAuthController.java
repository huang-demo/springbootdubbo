package com.mod.miniapp.web.user;

import com.mod.common.core.Result;
import com.mod.common.entity.vo.TokenVO;
import com.mod.common.web.BaseController;
import com.mod.user.entity.dto.UserInfoIdDTO;
import com.mod.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags="授权相关")
public class UserAuthController extends BaseController {
    @Reference
    private IUserInfoService userInfoService;

    @PostMapping("/getToken")
    @ApiOperation(value = "获取token", response = TokenVO.class)
    public Result getToken(@RequestBody UserInfoIdDTO userInfoId){
        TokenVO token = userInfoService.createToken(userInfoId.getUserId());
        return success(token);
    }
}
