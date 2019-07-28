package com.mod.common.redis;

import java.util.Set;

public class RedisAdapter implements JedisClient{
    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String set(String key, String val, Integer expire) {
        return null;
    }

    @Override
    public boolean lock(String key, String val, Long expire) {
        return false;
    }

    @Override
    public boolean unlock(String key, String val) {
        return false;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Long hset(String key, String item, String value) {
        return null;
    }

    @Override
    public String hget(String key, String item) {
        return null;
    }

    @Override
    public Long incr(String key) {
        return null;
    }

    @Override
    public Long incrBy(String key, Long val) {
        return null;
    }

    @Override
    public Long decr(String key) {
        return null;
    }

    @Override
    public Long decrBy(String key, Long decr) {
        return null;
    }

    @Override
    public Long expire(String key, int second) {
        return null;
    }

    @Override
    public Long ttl(String key) {
        return null;
    }

    @Override
    public Long del(String key) {
        return null;
    }

    @Override
    public Long hdel(String key, String item) {
        return null;
    }

    @Override
    public Long setNX(String key, String val, Integer expire) {
        return null;
    }

    @Override
    public Long sAdd(String key, String... value) {
        return null;
    }

    @Override
    public Set<String> sMembers(String key) {
        return null;
    }

    @Override
    public Set<String> sInter(String... key) {
        return null;
    }
}
