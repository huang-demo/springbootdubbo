package com.mod.admin.web.user;

import com.mod.admin.cache.AuthCache;
import com.mod.common.constant.SysConstant;
import com.mod.common.core.Result;
import com.mod.common.utils.JwtUtils;
import com.mod.common.web.BaseController;
import com.mod.user.entity.dto.LoginUserDTO;
import com.mod.user.entity.dto.SysUserDTO;
import com.mod.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "登录控制器")
public class LoginContoller extends BaseController {
    @Reference
    private IUserInfoService userInfoService;

    @Autowired
    private AuthCache authCache;

    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDTO dto) {
        SysUserDTO user = userInfoService.login(dto);
        //缓存用户
        authCache.cacheUserRole(user.getUserId());

        String token = JwtUtils.sign(user.getUserId(), user.getUserName(), SysConstant.LOGIN_SECRET);
        return success(token);
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest req, HttpServletResponse res) {
        String jwtToken = req.getHeader(SysConstant.TOKEN);
        SysUserDTO user = new SysUserDTO();
        user.setUserId(JwtUtils.getUserId(jwtToken));
        user.setUserName(JwtUtils.getUserName(jwtToken));
        userInfoService.logout(user);
        return success();
    }
}
