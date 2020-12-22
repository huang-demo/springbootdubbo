package com.mod.common.dubbo;

import com.mod.common.constant.RpcConstant;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.JwtUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/23 17:30
 */
@Activate(group = CommonConstants.PROVIDER)
@Slf4j
public class TraceServerFilter implements Filter{
    @Override
    public Result invoke(Invoker<?> invoker,Invocation invocation) throws RpcException{
        String traceId = RpcContext.getContext().getAttachment(RpcConstant.TRACE_KEY);
        boolean hasTrace = StringUtil.hasLength(traceId);
        if (hasTrace) {
            MDC.put(RpcConstant.TRACE_KEY, traceId);
        }else{
            traceId = "";
        }
        String token = RpcContext.getContext().getAttachment(RpcConstant.USER_TOKEN);
        Long startTime = System.currentTimeMillis();
        String className = invoker.getInterface().getName();
        String req = GsonUtils.obj2Json(invocation.getArguments());
        log.info("traceId- {},user:{}, method:{}.{}, request:{}", traceId, JwtUtils.getUserName(token),className , invocation.getMethodName(),req);
        try{
            Result result = invoker.invoke(invocation);
            Long takeTime = System.currentTimeMillis() - startTime;
            log.info("traceId- {}, method:{}.{},RES :{} ,耗时:{} ms",traceId,className , invocation.getMethodName(),GsonUtils.obj2Json(result.getValue()), takeTime);
            return result;
        }finally{
            RpcContext.getContext().clearAttachments();
            if(hasTrace){
                MDC.remove(RpcConstant.TRACE_KEY);
            }
        }

    }

}
