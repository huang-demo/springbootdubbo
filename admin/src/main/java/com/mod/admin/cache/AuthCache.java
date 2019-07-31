package com.mod.admin.cache;

import com.mod.admin.client.RedisBaseClient;
import com.mod.common.constant.RedisPrefixConstant;
import com.mod.common.exception.GlobalException;
import com.mod.common.utils.StringUtil;
import com.mod.sys.entity.bo.UrlRoleBO;
import com.mod.sys.entity.vo.RoleVO;
import com.mod.sys.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class AuthCache {
    @Autowired
    private RedisBaseClient redisClient;
    @Reference
    private IRoleService roleService;

    public void init() {
        List<UrlRoleBO> roleUrl = roleService.getRoleUrl();
        for (UrlRoleBO urlRoleBO : roleUrl) {
            if(StringUtil.isEmpty(urlRoleBO.getUrl())){
                continue;
            }
            redisClient.sAdd(getUrlKey(urlRoleBO.getUrl()), urlRoleBO.getRoleCode());
        }
    }

    private String getUrlKey(String url) {
        String sub = url.substring(1);
        return RedisPrefixConstant.URI_ROLES + sub.replaceAll("/", ":");
    }


    private String getUserKey(Long userId) {
        return RedisPrefixConstant.USER_ROLES + userId;
    }

    /**
     * @param userId
     */
    public void cacheUserRole(Long userId) {
        List<RoleVO> list = roleService.getUserRole(userId);
        String key = getUserKey(userId);
        for (RoleVO roleVO : list) {
            redisClient.sAdd(key, roleVO.getRoleCode());
        }
    }

    public Set<String> getRoles(String url) {
        return redisClient.sMembers(getUrlKey(url), String.class);
    }

    /**
     *
     * @param uri
     * @param userId
     */
    public void checkPermission(String uri, Long userId) {
        String urlKey = getUrlKey(uri);
        String userKey = getUserKey(userId);
        Long store = redisClient.sInterStore(urlKey, userKey);
        if (store != null && !store.equals(0L)) {
            return;
        }
        throw new GlobalException("权限不足");

    }
}
