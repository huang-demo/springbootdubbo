package com.mod.admin.filter;

import com.mod.admin.auth.JwtToken;
import com.mod.common.constant.RedisPrefixConstant;
import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
import com.mod.common.redis.RedisClient;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.StringUtil;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends BasicHttpAuthenticationFilter{

    @Value("${admin.isDev:false}")
    private boolean isDev;

    @Autowired
    private RedisClient redisClient;

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */

    @Override
    protected boolean isAccessAllowed(ServletRequest request,ServletResponse response,Object mappedValue) throws UnauthorizedException{
        try{
            executeLogin(request,response);
            return true;
        }catch(Exception e){
            //token 错误
            throw new GlobalException("token 校验不通过");
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request,ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String token = httpServletRequest.getHeader(SysConstant.TOKEN);
        if(isDev && StringUtil.isEmpty(token)){
            token = JwtUtils.sign(SysConstant.SUPPER_ADMIN,SysConstant.SUPPER_ADMIN_NAME,SysConstant.LOGIN_SECRET);
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 此处为AccessToken刷新，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    private boolean refreshToken(ServletRequest servletRequest,ServletResponse servletResponse){
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        //获取header，tokenStr
        String oldToken = request.getHeader(SysConstant.TOKEN);
        String userName = JwtUtils.getUserName(oldToken);
        String key = RedisPrefixConstant.USER_TOKEN + userName;
        //获取redis tokenStr
        String redisUserInfo = redisClient.get(key,String.class);
        if(redisUserInfo != null){
            if(oldToken.equals(redisUserInfo)){

                //                SecurityUtils.getSubject().login(jwtToken);
                //                response.setHeader(SysConstant.TOKEN, newTokenStr);
                return true;
            }
        }
        return false;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request,ServletResponse response) throws Exception{
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setHeader("Access-control-Allow-Origin",httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers",httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request,response);
    }

}
