package com.mod.miniapp.filter;


import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 17:26
 */
@Activate(group = CommonConstants.CONSUMER)
@Slf4j
public class ApiClientFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String traceId = MDC.get("traceId");
        if (StringUtil.hasLength(traceId)) {
            RpcContext.getContext().setAttachment("traceId", traceId);
        }
        String className = invoker.getInterface().getName();
        String req = GsonUtils.obj2Json(invocation.getArguments());
        log.info("traceId- {} method:{}.{},request:{}", traceId,className , invocation.getMethodName(),req);
        try{
            return invoker.invoke(invocation);
        }finally{
            RpcContext.getContext().clearAttachments();
        }
    }

}
