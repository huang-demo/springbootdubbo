package com.mod.admin.client;

import com.mod.common.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisClientSingle implements JedisClient {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.set(key, value);
    }

    @Override
    public String set(String key, String val, Integer expire) {
        Jedis jedis = jedisPool.getResource();
        return jedis.setex(key,expire.intValue(),val);
    }

    @Override
    public boolean lock(String key,String val,Long expire){
        Jedis jedis = jedisPool.getResource();

        return "OK".equals(jedis.set(key,val,"NX","EX",expire));
    }

    @Override
    public boolean unlock(String key,String val){
        return false;
    }

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

    @Override
    public Long hset(String key, String item, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hset(key, item, value);
    }

    @Override
    public String hget(String key, String item) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hget(key, item);
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.incr(key);
    }

    @Override
    public Long incrBy(String key,Long val){
        Jedis jedis = jedisPool.getResource();
        return jedis.incrBy(key,val);
    }

    @Override
    public Long decrBy(String key,Long decr){
        Jedis jedis = jedisPool.getResource();
        return jedis.decrBy(key,decr);
    }

    @Override
    public Long decr(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.decr(key);
    }

    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        return jedis.expire(key, second);
    }

    @Override
    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.ttl(key);
    }

    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.del(key);
    }

    @Override
    public Long hdel(String key, String item) {
        Jedis jedis = jedisPool.getResource();
        return jedis.hdel(key, item);
    }

    @Override
    public Long setNX(String key, String val, Integer expire) {
        Jedis jedis = jedisPool.getResource();
        Long setnx = jedis.setnx(key, val);
        if(setnx!=null){
            jedis.expire(key,expire);
        }
        return setnx;
    }
}