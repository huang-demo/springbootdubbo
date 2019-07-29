package com.mod.admin.client;

import com.mod.common.redis.JedisClient;
import com.mod.common.redis.RedisAdapter;
import com.mod.common.utils.ListUtil;
import com.mod.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class JedisClientSingle extends RedisAdapter implements JedisClient {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.set(key, value);
    }

    @Override
    public Integer batchAdd(Map<String,String> map){
        Jedis jedis = jedisPool.getResource();
        Pipeline pp = jedis.pipelined();
        for(Map.Entry<String,String> entry: map.entrySet()){
            pp.set(entry.getKey(),entry.getValue());
        }
        return pp.syncAndReturnAll().size();
    }

    @Override
    public String set(String key, String val, Integer expire) {
        Jedis jedis = jedisPool.getResource();
        return jedis.setex(key, expire.intValue(), val);
    }

    @Override
    public boolean lock(String key, String val, Long expire) {
        Jedis jedis = jedisPool.getResource();

        return "OK".equals(jedis.set(key, val, "NX", "EX", expire));
    }

    @Override
    public boolean unlock(String key, String val) {
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
    public Long incrBy(String key, Long val) {
        Jedis jedis = jedisPool.getResource();
        return jedis.incrBy(key, val);
    }

    @Override
    public Long decrBy(String key, Long decr) {
        Jedis jedis = jedisPool.getResource();
        return jedis.decrBy(key, decr);
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
        if (setnx != null) {
            jedis.expire(key, expire);
        }
        return setnx;
    }

    @Override
    public Long sAdd(String key, String... value) {
        Jedis jedis = jedisPool.getResource();
        return jedis.sadd(key, value);
    }

    @Override
    public Integer batchSAdd(Map<String,List<String>> map){
        Pipeline pipelined = jedisPool.getResource().pipelined();
        for(Map.Entry<String,List<String>> entry: map.entrySet()){
            List<String> list = entry.getValue();
            pipelined.sadd(entry.getKey(),ListUtil.list2Str(list).split(","));
        }
        List<Object> list = pipelined.syncAndReturnAll();
        return list.size();
    }

    @Override
    public Set<String> sMembers(String key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.smembers(key);
    }

    @Override
    public Set<String> sInter(String... key) {
        Jedis jedis = jedisPool.getResource();
        return jedis.sinter(key);
    }
}
