package com.mod.admin.interceptor;

import com.mod.common.constant.SysConstant;
import com.mod.common.core.GlobalRequest;
import com.mod.common.threadlocal.SessionRequestContent;
import com.mod.common.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        GlobalRequest gr = new GlobalRequest();

        gr.setUri(request.getRequestURI());
        gr.setUserName(JwtUtils.getUserName(request.getHeader(SysConstant.TOKEN)));
        gr.setStart(System.currentTimeMillis());
        gr.setIp(request.getRemoteAddr());
        SessionRequestContent.setRequest(gr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SessionRequestContent.print();
        SessionRequestContent.remove();
    }
}
