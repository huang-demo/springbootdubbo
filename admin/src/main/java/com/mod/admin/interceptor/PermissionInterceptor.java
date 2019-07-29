package com.mod.admin.interceptor;

import com.mod.admin.cache.AuthCache;
import com.mod.common.constant.SysConstant;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/29 10:17
 */
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor{
    @Value("${admin.isDev}")
    private boolean isDev;
    @Autowired
    private AuthCache authCache;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{

        log.info("校验当前用户是否拥有权限");
        //开发环境或者超级管理员不管直接放行
        if (isDev || SysConstant.SUPPER_ADMIN.equals(JwtUtils.getUserId(request.getHeader(SysConstant.TOKEN)))) {
            return true;
        }
        String uri = request.getRequestURI();
        Set<String> roles = authCache.getRoles(uri);
        log.info("uri:{},拥有权限的角色有:{}",uri,ListUtil.list2Str(roles));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{

    }
}
