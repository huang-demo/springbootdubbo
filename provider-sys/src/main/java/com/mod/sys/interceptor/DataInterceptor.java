package com.mod.sys.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * 数据权限拦截
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class })
})
@Component
@Slf4j
public class DataInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            BoundSql boundSql = handler.getBoundSql();
            String sql = boundSql.getSql();
            //TODO
            log.info("数据拦截。。。。");

        }

         return invocation.proceed();
    }

    @Override
    public Object plugin(Object invocation) {

        return Plugin.wrap(invocation, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
