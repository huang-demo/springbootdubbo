package com.mod.admin.interceptor;

import com.mod.common.constant.SysConstant;
import com.mod.common.core.SysUser;
import com.mod.common.threadlocal.SessionContent;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/31 14:17
 */
@Component
public class SessionInterceptor implements HandlerInterceptor{

    @Value("${admin.isDev:false}")
    private boolean isDev;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        String token = request.getHeader(SysConstant.TOKEN);
        //开发环境并且token不存在
        if(isDev && StringUtil.isEmpty(token)){
            token = JwtUtils.sign(SysConstant.SUPPER_ADMIN,SysConstant.SUPPER_ADMIN_NAME,SysConstant.LOGIN_SECRET);
        }
        SysUser user = new SysUser();
        user.setUserId(JwtUtils.getUserId(token));
        user.setOpenid(JwtUtils.getOpenid(token));
        user.setUserName(JwtUtils.getUserName(token));

        SessionContent.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{

        SessionContent.remove();
    }
}
