package com.mod.admin.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.mod.admin.auth.JwtToken;
import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
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
 * @Date create in 2019/7/30 17:35
 */
//@Component
public class JwtInterceptor implements HandlerInterceptor{

    @Value("${admin.isDev:false}")
    private boolean isDev;
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        String token = request.getHeader(SysConstant.TOKEN);
        if(isDev && StringUtil.isEmpty(token)){
            token = JwtUtils.sign(SysConstant.SUPPER_ADMIN,SysConstant.SUPPER_ADMIN_NAME,SysConstant.LOGIN_SECRET);
        }
        boolean verify = JwtUtils.verify(token,SysConstant.LOGIN_SECRET);
        if(!verify){
            throw new GlobalException("token 校验不通过");
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
