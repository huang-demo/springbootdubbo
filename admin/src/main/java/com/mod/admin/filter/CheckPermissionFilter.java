package com.mod.admin.filter;

import com.mod.common.constant.SysConstant;
import com.mod.common.exception.GlobalException;
import com.mod.common.redis.JedisClient;
import com.mod.common.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class CheckPermissionFilter implements Filter {
    @Value("${admin.isDev}")
    private boolean isDev;
    @Autowired
    private JedisClient jedisClient;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        boolean hasPermission = checkPermission(request);
        if (!hasPermission) {
            throw new GlobalException("权限不足");
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean checkPermission(HttpServletRequest request) {
        log.info("校验当前用户是否拥有权限");
        //开发环境或者超级管理员不管直接放行
        if (isDev || SysConstant.SUPPER_ADMIN.equals(JwtUtils.getUserId(request.getHeader(SysConstant.TOKEN)))) {
            return true;
        }
        String uri = request.getRequestURI();
        String key = "auth.string."+uri.replaceAll("/", ".");

        return true;


    }

}
