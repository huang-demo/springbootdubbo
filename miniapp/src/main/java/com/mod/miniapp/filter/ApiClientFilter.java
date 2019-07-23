package com.mod.miniapp.filter;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 17:26
 */
@Activate(group = Constants.CONSUMER)
@Slf4j
public class ApiClientFilter implements Filter{

    @Override
    public Result invoke(Invoker<?> invoker,Invocation invocation) throws RpcException{
        String traceId = MDC.get("traceId");
        if (StringUtils.isNotBlank(traceId)) {
            RpcContext.getContext().setAttachment("traceId", traceId);
        }
        log.info("apiClientFilter {}",traceId);
        return invoker.invoke(invocation);
    }

}
