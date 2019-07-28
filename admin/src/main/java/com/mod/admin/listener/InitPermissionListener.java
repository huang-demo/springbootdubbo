package com.mod.admin.listener;

import com.mod.admin.cache.UrlRoleCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 将权限数据全部缓存到redis
 */

@Component
@Slf4j
public class InitPermissionListener implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    private ApplicationContext ctx;
    @Autowired
    private UrlRoleCache urlRoleCache;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("------初始化权限集合-----");
        //path:[role1,role2]
        urlRoleCache.init();
    }
}