package com.mod.miniapp.client;

import com.mod.common.redis.RedisAdapter;
import com.mod.common.redis.RedisClient;
import com.mod.common.utils.ProtoStuffUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 描述当前类
 * @Author Mr.p
 * @Date create in 2019/7/30 10:21
 */
@Component
@Slf4j
public class RedisBaseClient extends RedisAdapter implements RedisClient{

    private final RedisTemplate<String,String> redisTemplate;

    @Autowired
    public RedisBaseClient(RedisTemplate<String,String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * get cache
     *
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T get(final String field,Class<T> targetClass){
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>)connection -> connection.get(field.getBytes()));
        if(result == null){
            return null;
        }

        return ProtoStuffUtil.deserialize(result,targetClass);
    }

    /**
     * put cache
     *
     * @param field
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public <T> void set(String field,T obj){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.set(field.getBytes(),value);
            return null;
        });
    }

    /**
     * put cache with expire time
     *
     * @param field
     * @param obj
     * @param expireTime 单位: s
     * @param <T>
     */
    @Override
    public <T> void setEx(String field,T obj,final long expireTime){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.setEx(field.getBytes(),expireTime,value);
            return null;
        });
    }

    /**
     * get list cache
     *
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> getList(final String field,Class<T> targetClass){
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>)connection -> connection.get(field.getBytes()));
        if(result == null){
            return Collections.EMPTY_LIST;
        }

        return ProtoStuffUtil.deserializeList(result,targetClass);
    }

    /**
     * put list cache
     *
     * @param field
     * @param objList
     * @param <T>
     * @return
     */
    @Override
    public <T> void setList(String field,List<T> objList){
        final byte[] value = ProtoStuffUtil.serializeList(objList);
        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.set(field.getBytes(),value);
            return null;
        });
    }

    /**
     * put list cache with expire time
     *
     * @param field
     * @param objList
     * @param expireTime
     * @param <T>
     * @return
     */
    @Override
    public <T> void setListEx(String field,List<T> objList,final long expireTime){
        final byte[] value = ProtoStuffUtil.serializeList(objList);
        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.setEx(field.getBytes(),expireTime,value);
            return null;
        });
    }

    /**
     * get h cache
     *
     * @param key
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T hGet(final String key,final String field,Class<T> targetClass){
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>)connection -> connection.hGet(key.getBytes(),field.getBytes()));
        if(result == null){
            return null;
        }

        return ProtoStuffUtil.deserialize(result,targetClass);
    }

    /**
     * put hash cache
     *
     * @param key
     * @param field
     * @param obj
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean hSet(String key,String field,T obj){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        return redisTemplate.execute((RedisCallback<Boolean>)connection -> connection.hSet(key.getBytes(),field.getBytes(),value));
    }

    /**
     * put hash cache
     *
     * @param key
     * @param field
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void hSetEx(String key,String field,T obj,long expireTime){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.hSet(key.getBytes(),field.getBytes(),value);
            connection.expire(key.getBytes(),expireTime);
            return null;
        });
    }

    /**
     * get list cache
     *
     * @param key
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> hGetList(final String key,final String field,Class<T> targetClass){
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>)connection -> connection.hGet(key.getBytes(),field.getBytes()));
        if(result == null){
            return null;
        }

        return ProtoStuffUtil.deserializeList(result,targetClass);
    }

    /**
     * put list cache
     *
     * @param key
     * @param field
     * @param objList
     * @param <T>
     * @return
     */
    @Override
    public <T> boolean hSetList(String key,String field,List<T> objList){
        final byte[] value = ProtoStuffUtil.serializeList(objList);
        return redisTemplate.execute((RedisCallback<Boolean>)connection -> connection.hSet(key.getBytes(),field.getBytes(),value));
    }

    /**
     * get cache by keys
     *
     * @param key
     * @param fields
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String,T> hMGet(String key,Collection<String> fields,Class<T> targetClass){
        List<byte[]> byteFields = fields.stream().map(String::getBytes).collect(Collectors.toList());
        byte[][] queryFields = new byte[byteFields.size()][];
        byteFields.toArray(queryFields);
        List<byte[]> cache = redisTemplate.execute((RedisCallback<List<byte[]>>)connection -> connection.hMGet(key.getBytes(),queryFields));

        Map<String,T> results = new HashMap<>(16);
        Iterator<String> it = fields.iterator();
        int index = 0;
        while(it.hasNext()){
            String k = it.next();
            if(cache.get(index) == null){
                index++;
                continue;
            }

            results.put(k,ProtoStuffUtil.deserialize(cache.get(index),targetClass));
            index++;
        }

        return results;
    }

    /**
     * set cache by keys
     *
     * @param field
     * @param values
     * @param <T>
     */
    @Override
    public <T> void hMSet(String field,Map<String,T> values){
        Map<byte[],byte[]> byteValues = new HashMap<>(16);
        for(Map.Entry<String,T> value: values.entrySet()){
            byteValues.put(value.getKey().getBytes(),ProtoStuffUtil.serialize(value.getValue()));
        }

        redisTemplate.execute((RedisCallback<Void>)connection -> {
            connection.hMSet(field.getBytes(),byteValues);
            return null;
        });
    }

    /**
     * get caches in hash
     *
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> Map<String,T> hGetAll(String key,Class<T> targetClass){
        Map<byte[],byte[]> records = redisTemplate.execute((RedisCallback<Map<byte[],byte[]>>)connection -> connection.hGetAll(key.getBytes()));

        Map<String,T> ret = new HashMap<>(16);
        for(Map.Entry<byte[],byte[]> record: records.entrySet()){
            T obj = ProtoStuffUtil.deserialize(record.getValue(),targetClass);
            ret.put(new String(record.getKey()),obj);
        }

        return ret;
    }

    /**
     * list index
     *
     * @param key
     * @param index
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T lIndex(String key,int index,Class<T> targetClass){
        byte[] value = redisTemplate.execute((RedisCallback<byte[]>)connection -> connection.lIndex(key.getBytes(),index));
        return ProtoStuffUtil.deserialize(value,targetClass);
    }

    /**
     * list range
     *
     * @param key
     * @param start
     * @param end
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> List<T> lRange(String key,int start,int end,Class<T> targetClass){
        List<byte[]> value = redisTemplate.execute((RedisCallback<List<byte[]>>)connection -> connection.lRange(key.getBytes(),start,end));
        return value.stream().map(record -> ProtoStuffUtil.deserialize(record,targetClass)).collect(Collectors.toList());
    }

    /**
     * list left push
     *
     * @param key
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void lPush(String key,T obj){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Long>)connection -> connection.lPush(key.getBytes(),value));
    }


    /**
     * list left push
     *
     * @param key
     * @param objList
     * @param <T>
     */
    @Override
    public <T> void lPush(String key,List<T> objList){
        List<byte[]> byteFields = objList.stream().map(ProtoStuffUtil::serialize).collect(Collectors.toList());
        byte[][] values = new byte[byteFields.size()][];

        redisTemplate.execute((RedisCallback<Long>)connection -> connection.lPush(key.getBytes(),values));
    }

    /**
     * @param key
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void sAdd(String key,T obj){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Long>)connection -> connection.sAdd(key.getBytes(),value));
    }

    /**
     * @param key
     * @param obj
     * @param <T>
     */
    @Override
    public <T> void sIsMember(String key,T obj){
        final byte[] value = ProtoStuffUtil.serialize(obj);
        redisTemplate.execute((RedisCallback<Boolean>)connection -> connection.sIsMember(key.getBytes(),value));
    }

    /**
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    @Override
    public <T> Set<T> sMembers(String key,Class<T> targetClass){
        Set<byte[]> value = redisTemplate.execute((RedisCallback<Set<byte[]>>)connection -> connection.sMembers(key.getBytes()));
        return value.stream().map(record -> ProtoStuffUtil.deserialize(record,targetClass)).collect(Collectors.toSet());
    }

    @Override
    public Long sInterStore(String k1,String k2) {
        Long size = redisTemplate.execute((RedisCallback<Long>) connection -> connection.sInterStore(k1.getBytes(), k2.getBytes()));
        return size;
    }

    /**
     * 精确删除key
     *
     * @param key
     */
    @Override
    public void delete(String key){
        redisTemplate.delete(key);
    }


    /**
     * 排行榜的存入
     *
     * @param redisKey
     * @param immutablePair
     */
    public void zAdd(String redisKey,ImmutablePair<String,BigDecimal> immutablePair){
        final byte[] key = redisKey.getBytes();
        final byte[] value = immutablePair.getLeft().getBytes();
        redisTemplate.execute((RedisCallback<Boolean>)connection -> connection.zAdd(key,immutablePair.getRight().doubleValue(),value));

    }

    /**
     * 获取排行榜低->高排序
     *
     * @param redisKey 要进行排序的类别
     * @param start
     * @param end
     * @return
     */
    public List<ImmutablePair<String,BigDecimal>> zRangeWithScores(String redisKey,int start,int end){
        Set<RedisZSetCommands.Tuple> items = redisTemplate.execute((RedisCallback<Set<RedisZSetCommands.Tuple>>)connection -> connection.zRangeWithScores(redisKey.getBytes(),start,end));
        return items.stream().map(record -> ImmutablePair.of(new String(record.getValue()),BigDecimal.valueOf(record.getScore()))).collect(Collectors.toList());
    }


    /**
     * 获取排行榜高->低排序
     *
     * @param redisKey 要进行排序的类别
     * @param start
     * @param end
     * @return
     */
    public List<ImmutablePair<String,BigDecimal>> zRevRangeWithScores(String redisKey,int start,int end){
        Set<RedisZSetCommands.Tuple> items = redisTemplate.execute((RedisCallback<Set<RedisZSetCommands.Tuple>>)connection -> connection.zRevRangeWithScores(redisKey.getBytes(),start,end));
        return items.stream().map(record -> ImmutablePair.of(new String(record.getValue()),BigDecimal.valueOf(record.getScore()))).collect(Collectors.toList());
    }

    @Override
    public Long incr(String key){
        Long len = redisTemplate.execute((RedisCallback<Long>)connection -> {
            return connection.incr(key.getBytes());
        });
        return len;
    }

    @Override
    public Long incrBy(String key,final Long data){
        Long len = redisTemplate.execute((RedisCallback<Long>)connection -> {
            return connection.incrBy(key.getBytes(),data);
        });
        return len;
    }

    @Override
    public Long decr(String key){
        Long len = redisTemplate.execute((RedisCallback<Long>)connection -> {
            return connection.decr(key.getBytes());
        });
        return len;
    }

    @Override
    public Long decrBy(String key,final Long data){
        Long len = redisTemplate.execute((RedisCallback<Long>)connection -> {
            return connection.decrBy(key.getBytes(),data);
        });
        return len;
    }

    @Override
    public <T> Boolean setNx(String key,Long expire,T obj){
        Boolean nx = redisTemplate.execute((RedisCallback<Boolean>)connection -> {
            byte[] keyBytes = key.getBytes();
            byte[] serialize = ProtoStuffUtil.serialize(obj);
            Boolean isOk = connection.setNX(keyBytes,serialize);
            if(isOk){
                connection.expire(keyBytes,expire);
            }
            return isOk;
        });
        return nx;
    }
}
