package com.mod.miniapp.interceptor;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mod.common.annotation.PassToken;
import com.mod.common.annotation.UserLoginToken;
import com.mod.common.constant.ExceptionCode;
import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor{

    @Value("${miniapp.check-auth:true}")
    private Boolean checkAuth;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object object) throws Exception{

        if(!checkAuth){
            return true;
        }
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(SysConstant.TOKEN);
        HandlerMethod handlerMethod = (HandlerMethod)object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if(method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if(passToken.required()){
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                // 执行认证
                if(token == null){
                    throw new GlobalException(ExceptionCode.NO_LOGIN);
                }
                // 获取 token 中的 user id
                String openid;
                String salt;
                String userName;
                try{
                    DecodedJWT decode = JWT.decode(token);

                    openid = decode.getClaim("openid").asString();
                    salt = decode.getClaim("secret").asString();
                    userName = decode.getClaim("userName").asString();

                }catch(JWTDecodeException j){
                    throw new GlobalException(ExceptionCode.NO_LOGIN);
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(salt)).build();
                try{
                    jwtVerifier.verify(token);
//                    SessionUserContent.setSysUserVO(new SysUserVO(openid,userName));
                }catch(JWTVerificationException e){
                    throw new GlobalException("Token 校验不通过");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o,ModelAndView modelAndView) throws Exception{

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object o,Exception e) throws Exception{

    }
}
