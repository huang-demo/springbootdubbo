package com.mod.common.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisClient{

    /**
     * get cache
     *
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> T get(final String field,Class<T> targetClass);

    /**
     * put cache
     *
     * @param field
     * @param obj
     * @param <T>
     * @return
     */
    <T> void set(String field,T obj);

    /**
     * put cache with expire time
     *
     * @param field
     * @param obj
     * @param expireTime 单位: s
     * @param <T>
     */
    <T> void setEx(String field,T obj,final long expireTime);

    /**
     * get list cache
     *
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> List<T> getList(final String field,Class<T> targetClass);

    /**
     * put list cache
     *
     * @param field
     * @param objList
     * @param <T>
     * @return
     */
    <T> void setList(String field,List<T> objList);

    /**
     * put list cache with expire time
     *
     * @param field
     * @param objList
     * @param expireTime
     * @param <T>
     * @return
     */
    <T> void setListEx(String field,List<T> objList,final long expireTime);

    /**
     * get h cache
     *
     * @param key
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> T hGet(final String key,final String field,Class<T> targetClass);

    /**
     * put hash cache
     *
     * @param key
     * @param field
     * @param obj
     * @param <T>
     * @return
     */
    <T> boolean hSet(String key,String field,T obj);

    /**
     * put hash cache
     *
     * @param key
     * @param field
     * @param obj
     * @param <T>
     */
    <T> void hSetEx(String key,String field,T obj,long expireTime);

    /**
     * get list cache
     *
     * @param key
     * @param field
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> List<T> hGetList(final String key,final String field,Class<T> targetClass);

    /**
     * put list cache
     *
     * @param key
     * @param field
     * @param objList
     * @param <T>
     * @return
     */
    <T> boolean hSetList(String key,String field,List<T> objList);

    /**
     * get cache by keys
     *
     * @param key
     * @param fields
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> Map<String,T> hMGet(String key,Collection<String> fields,Class<T> targetClass);

    /**
     * set cache by keys
     *
     * @param field
     * @param values
     * @param <T>
     */
    <T> void hMSet(String field,Map<String,T> values);

    /**
     * get caches in hash
     *
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> Map<String,T> hGetAll(String key,Class<T> targetClass);

    /**
     * list index
     *
     * @param key
     * @param index
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> T lIndex(String key,int index,Class<T> targetClass);

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
    <T> List<T> lRange(String key,int start,int end,Class<T> targetClass);

    /**
     * list left push
     *
     * @param key
     * @param obj
     * @param <T>
     */
    <T> void lPush(String key,T obj);

    /**
     * list left push
     *
     * @param key
     * @param objList
     * @param <T>
     */
    <T> void lPush(String key,List<T> objList);

    /**
     * @param key
     * @param obj
     * @param <T>
     */
    <T> void sAdd(String key,T obj);

    /**
     * @param key
     * @param obj
     * @param <T>
     */
    <T> void sIsMember(String key,T obj);

    /**
     * @param key
     * @param targetClass
     * @param <T>
     * @return
     */
    <T> Set<T> sMembers(String key,Class<T> targetClass);

    /**
     * 精确删除key
     *
     * @param key
     */
    void delete(String key);


    /**
     * @param key
     */
    Long incr(String key);


    /**
     * @param key
     * @param data
     * @return
     */
    Long incrBy(String key,Long data);

    /**
     * @param key
     * @return
     */
    Long decr(String key);

    /**
     * @param key
     * @param data
     * @return
     */
    Long decrBy(String key,Long data);

    /**
     * @param key
     * @param expire
     * @param obj
     * @param <T>
     * @return
     */

    <T> Boolean setNx(String key,Long expire,T obj);
}
