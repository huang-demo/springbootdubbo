package com.mod.admin.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mod.admin.auth.JwtToken;
import com.mod.common.constant.SysConstant;
import com.mod.common.core.Result;
import com.mod.common.utils.JwtUtils;
import com.mod.common.web.BaseController;
import com.mod.user.entity.dto.LoginUserDTO;
import com.mod.user.entity.dto.SysUserDTO;
import com.mod.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
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

    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDTO dto, HttpServletResponse response) {
        SysUserDTO user = userInfoService.login(dto);
        String token = JwtUtils.sign(user.getUserId(), user.getUserName(), SysConstant.LOGIN_SECRET);
        JwtToken jwtToken = new JwtToken(token);
        response.setHeader(SysConstant.TOKEN, token);
        SecurityUtils.getSubject().login(jwtToken);
        return success();
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest req, HttpServletResponse res) {
        String jwtToken = req.getHeader(SysConstant.TOKEN);

//        userInfoService.logout(jwtToken);
        res.setHeader(SysConstant.TOKEN, null);
        return success();
    }
}
