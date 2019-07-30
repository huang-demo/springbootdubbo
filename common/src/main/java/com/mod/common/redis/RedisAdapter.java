package com.mod.common.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisAdapter implements RedisClient{

    @Override
    public <T> T get(String field,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> void set(String field,T obj){

    }

    @Override
    public <T> void setEx(String field,T obj,long expireTime){

    }

    @Override
    public <T> List<T> getList(String field,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> void setList(String field,List<T> objList){

    }

    @Override
    public <T> void setListEx(String field,List<T> objList,long expireTime){

    }

    @Override
    public <T> T hGet(String key,String field,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> boolean hSet(String key,String field,T obj){
        return false;
    }

    @Override
    public <T> void hSetEx(String key,String field,T obj,long expireTime){

    }

    @Override
    public <T> List<T> hGetList(String key,String field,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> boolean hSetList(String key,String field,List<T> objList){
        return false;
    }

    @Override
    public <T> Map<String,T> hMGet(String key,Collection<String> fields,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> void hMSet(String field,Map<String,T> values){

    }

    @Override
    public <T> Map<String,T> hGetAll(String key,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> T lIndex(String key,int index,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> List<T> lRange(String key,int start,int end,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> void lPush(String key,T obj){

    }

    @Override
    public <T> void lPush(String key,List<T> objList){

    }

    @Override
    public <T> void sAdd(String key,T obj){

    }


    @Override
    public <T> void sIsMember(String key,T obj){

    }

    @Override
    public <T> Set<T> sMembers(String key,Class<T> targetClass){
        return null;
    }

    @Override
    public <T> Set<T> sInter(String... keys) {
        return null;
    }

    @Override
    public Long sInterStore(String k1,String k2) {
        return null;
    }

    @Override
    public void delete(String key){

    }

    @Override
    public Long incr(String key){
        return null;
    }

    @Override
    public Long incrBy(String key,Long data){
        return null;
    }

    @Override
    public Long decr(String key){
        return null;
    }

    @Override
    public Long decrBy(String key,Long data){
        return null;
    }

    @Override
    public <T> Boolean setNx(String key,Long expire,T obj){
        return false;
    }
}
