package com.mod.user.filter;

import com.mod.common.constant.RpcConstant;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 17:30
 */
@Activate(group = "provider-user")
@Slf4j
public class TraceServerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = RpcContext.getContext().getAttachment(RpcConstant.TRACE_KEY);
        String token = RpcContext.getContext().getAttachment(RpcConstant.USER_TOKEN);
        if (StringUtil.hasLength(traceId)) {
            MDC.put("traceId", traceId);
        }
        Long startTime = System.currentTimeMillis();
        String className = invoker.getInterface().getName();
        String param = GsonUtils.obj2Json(invocation.getArguments());
        log.info("traceId- {},user:{}, method:{}.{}, request:{}", traceId, JwtUtils.getUserName(token),className , invocation.getMethodName(),param);
        Result result = invoker.invoke(invocation);
        Long takeTime = System.currentTimeMillis() - startTime;
        log.info("traceId- {}, method:{}.{}, response:{}, time:{} ms",traceId,className , takeTime);
        return result;
    }

}
