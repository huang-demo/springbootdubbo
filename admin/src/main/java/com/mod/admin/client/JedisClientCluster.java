package com.mod.admin.client;

import com.mod.common.redis.JedisClient;
import com.mod.common.redis.RedisAdapter;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

@Component
public class JedisClientCluster extends RedisAdapter implements JedisClient {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String set(String key, String val, Integer expire) {
        return jedisCluster.setex(key,expire,val);
    }

    @Override
    public boolean lock(String key,String val,Long expire){
        return "OK".equals(jedisCluster.set(key,val,"NX","EX",expire));
    }

    @Override
    public boolean unlock(String key,String val){
        String s = jedisCluster.get(key);
        if(StringUtils.hasLength(s)&&s.equals(val)){
            jedisCluster.del(key);
            return true;
        }
        return false;
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public Long hset(String key, String item, String value) {
        return jedisCluster.hset(key,item,value);
    }

    @Override
    public String hget(String key, String item) {
        return jedisCluster.hget(key, item);
    }

    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    @Override
    public Long incrBy(String key,Long val){

        return jedisCluster.incrBy(key,val);
    }

    @Override
    public Long decrBy(String key,Long val){
        return jedisCluster.decrBy(key,val);
    }

    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    @Override
    public Long expire(String key, int second) {
        return jedisCluster.expire(key,second);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Long hdel(String key, String item) {
        return jedisCluster.hdel(key,item);
    }

    @Override
    public Long setNX(String key, String val, Integer expire) {
        Long setnx = jedisCluster.setnx(key, val);
        if(setnx>0){
            jedisCluster.expire(key,expire);
        }
        return setnx;
    }

    @Override
    public Long sAdd(String key,String... values){
        return jedisCluster.sadd(key,values);
    }

    @Override
    public Set<String> sMembers(String key){
        return jedisCluster.smembers(key);
    }

    @Override
    public Set<String> sInter(String... keys){
        return jedisCluster.sinter(keys);
    }
}
