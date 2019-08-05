package com.mod.sys.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 17:30
 */
@Activate
@Slf4j
public class TraceServerFilter implements Filter{
    @Override
    public Result invoke(Invoker<?> invoker,Invocation invocation) throws RpcException{
        String traceId = RpcContext.getContext().getAttachment("traceId");
        if (StringUtil.hasLength(traceId)) {
            MDC.put("traceId", traceId);
        }
        Long startTime = System.currentTimeMillis();
        String className = invoker.getInterface().getName();
        String req = GsonUtils.obj2Json(invocation.getArguments());
        log.info("traceId- {}, method:{}.{}, request:{}", traceId,className , invocation.getMethodName(),req);
        Result result = invoker.invoke(invocation);
        Long takeTime = System.currentTimeMillis() - startTime;
        log.info("traceId- {}, method:{}.{}, time:{} ms",traceId,className , invocation.getMethodName(), String.valueOf(takeTime));
        return result;
    }

}
