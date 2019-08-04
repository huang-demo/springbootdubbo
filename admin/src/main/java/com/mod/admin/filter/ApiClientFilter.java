package com.mod.admin.filter;


import com.mod.common.constant.RpcConstant;
import com.mod.common.threadlocal.SessionRequestContent;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 17:26
 */
@Activate(group = "admin-filter")
@Slf4j
public class ApiClientFilter implements Filter{

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException{
        String traceId = MDC.get(RpcConstant.TRACE_KEY);
        SessionRequestContent.getRequest();
        String token = MDC.get(RpcConstant.USER_TOKEN);
        if(StringUtil.hasLength(traceId)){
            RpcContext.getContext().setAttachment(RpcConstant.TRACE_KEY, traceId);
        }
        if(StringUtil.hasLength(token)){
            RpcContext.getContext().setAttachment(RpcConstant.USER_TOKEN, token);
        }
        String className = invoker.getInterface().getName();
        String param = GsonUtils.obj2Json(invocation.getArguments());
        log.info("traceId- {} method:{}.{},request:{}", traceId, className, invocation.getMethodName(), param);
        return invoker.invoke(invocation);
    }

}
