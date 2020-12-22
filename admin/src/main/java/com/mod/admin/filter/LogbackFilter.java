package com.mod.admin.filter;

import com.mod.common.constant.RpcConstant;
import com.mod.common.constant.SysConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/23 16:20
 */
@Slf4j
public class LogbackFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        boolean bInsertMDC = putTraceId();
        boolean putToken = putToken(request);
        try {
            chain.doFilter(request, response);
        } finally {
            if (bInsertMDC) {
                MDC.remove(RpcConstant.TRACE_KEY);
            }
            if(putToken){
                MDC.remove(RpcConstant.USER_TOKEN);
            }
        }

    }

    private boolean putTraceId() {
        MDC.put(RpcConstant.TRACE_KEY, UUID.randomUUID().toString());
        return true;
    }
    private boolean putToken(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(SysConstant.TOKEN);
        boolean flag = (token != null);
        if(flag){
            MDC.put(RpcConstant.USER_TOKEN, token);
        }
        return flag;
    }

    @Override
    public void destroy() {

    }

}
