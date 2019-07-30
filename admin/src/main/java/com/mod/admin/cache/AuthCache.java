package com.mod.admin.cache;

import com.mod.admin.client.RedisBaseClient;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.entity.vo.RoleVO;
import com.mod.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class AuthCache{
    private static final String prefix = "auth:set";
    @Autowired
    private RedisBaseClient redisClient;
    @Reference
    private IRoleService roleService;

    public void init(){
        List<UrlRoleBO> roleUrl = roleService.getRoleUrl();
        for(UrlRoleBO urlRoleBO: roleUrl){
            redisClient.sAdd(getUrlKey(urlRoleBO.getUrl()),urlRoleBO.getRoleCode());
        }
    }

    private String getUrlKey(String url){
        return prefix + url.replaceAll("/",":");
    }

    public Set<String> getRoles(String url){
        return redisClient.sMembers(getUrlKey(url),String.class);
    }
}
