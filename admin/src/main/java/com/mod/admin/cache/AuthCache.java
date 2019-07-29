package com.mod.admin.cache;

import com.mod.common.redis.JedisClient;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class AuthCache{
    private static final String prefix = "auth.set";
    @Autowired
    private JedisClient jedisClient;
    @Reference
    private IRoleService roleService;

    public void init() {
        List<UrlRoleBO> list = roleService.getRoleUrl();
        log.info("size:{}",list.size());
        for (UrlRoleBO bo : list) {
            String key = getUrlKey(bo.getUrl());
            log.info("add cache {}:{}",key,bo.getRoleCode());
            log.info("res:{}",jedisClient.sAdd(key, bo.getRoleCode()));
        }

    }

    private String getUrlKey(String url){
        return prefix+url.replaceAll("/",".");
    }

    public Set<String> getRoles(String url){
        return jedisClient.sMembers(getUrlKey(url));
    }
}