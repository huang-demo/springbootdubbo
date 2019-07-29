package com.mod.admin.cache;

import com.mod.common.redis.JedisClient;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class AuthCache{
    private static final String prefix = "auth:string";
    @Autowired
    private JedisClient jedisClient;
    @Reference
    private IRoleService roleService;

    public void init() {

        List<UrlRoleBO> list = roleService.getRoleUrl();
        Map<String,List<String>> map = new HashMap<>(list.size()/2);
        for (UrlRoleBO bo : list) {
            String key = getUrlKey(bo.getUrl());
            if(map.containsKey(key)){
                map.get(key).add(bo.getRoleCode());
            }else{
                List<String> sb = new ArrayList<>(5);
                sb.add(bo.getRoleCode());
                map.put(key,sb);
            }
        }

        Integer count = jedisClient.batchSAdd(map);

        log.info("init url role ,size:{}",count);
    }

    private String getUrlKey(String url){
        return prefix+url.replaceAll("/",":");
    }

    public Set<String> getRoles(String url){
        return jedisClient.sMembers(getUrlKey(url));
    }
}
