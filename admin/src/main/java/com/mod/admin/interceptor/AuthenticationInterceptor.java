package com.mod.admin.interceptor;

import com.mod.admin.filter.JwtFilter;
import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/8/7 10:02
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor{
    @Value("${admin.isDev:false}")
    private boolean isDev;

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{

        String token = request.getHeader(SysConstant.TOKEN);
        if(isDev && StringUtil.isEmpty(token)){
            token = JwtUtils.sign(SysConstant.SUPPER_ADMIN, SysConstant.SUPPER_ADMIN_NAME, SysConstant.LOGIN_SECRET);
        }
        boolean flag = false;
        try{
            flag = JwtUtils.verify(token,SysConstant.LOGIN_SECRET);
        }catch(Exception e){
            log.info("token 授权不通过,err:{}",e.getMessage());
        }
        if(!flag){
            throw new GlobalException("token 授权不通过");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{

    }
}
