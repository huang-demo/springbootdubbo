package com.mod.admin.shrio;

import com.mod.admin.auth.JwtToken;
import com.mod.common.constant.SysConstant;
import com.mod.common.utils.JwtUtils;
import com.mod.user.entity.dto.SysUserDTO;
import com.mod.user.service.IUserInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

    @Reference
    private IUserInfoService userInfoService;

    public MyRealm() {
        setAuthenticationTokenClass(JwtToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtils.getUserName(principalCollection.toString());

        SysUserDTO user = userInfoService.findByUserName(username);
        //根据用户ID查询角色（role），放入到Authorization里。

        //根据用户ID查询权限（permission），放入到Authorization里。
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtils.getUserName(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }
//        SysUserDTO user = userInfoService.findByUserName(username);

        if (!JwtUtils.verify(token, username, SysConstant.LOGIN_SECRET)) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        // TODO Auto-generated method stub
        return token instanceof JwtToken;
    }
}
