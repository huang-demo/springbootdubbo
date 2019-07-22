package com.mod.common.redis;

public interface JedisClient {
    /**
     * 设置字符串
     *
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     *
     * @param key
     * @param val
     * @param expire 秒
     * @return
     */
    String set(String key, String val, Integer expire);

    /**
     *
     * @param key
     * @param val
     * @param expire
     * @return
     */
    boolean lock(String key, String val, Long expire);

    /**
     *
     * @param key
     * @param val
     * @return
     */
    boolean unlock(String key, String val);

    /**
     * 获取
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置 key 指定的哈希集中指定字段的值。
     * <p>
     * 如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。
     * <p>
     * 如果字段在哈希集中存在，它将被重写
     */
    Long hset(String key, String item, String value);

    /**
     * 返回 key 指定的哈希集中该字段所关联的值
     *
     * @param key
     * @param item
     * @return
     */
    String hget(String key, String item);

    /**
     * 对存储在指定key的数值执行原子的加1操作。
     *
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     *
     * @param key
     * @param val
     * @return
     */
    Long incrBy(String key, Long val);

    /**
     * 对存储在指定key的数值执行原子的减1操作。
     *
     * @param key
     * @return
     */
    Long decr(String key);

    /**
     *
     * @param key
     * @param decr
     * @return
     */
    Long decrBy(String key, Long decr);

    /**
     * 设置指定key的有效期
     *
     * @param key
     * @param second
     * @return
     */
    Long expire(String key, int second);

    /**
     * 返回key剩余的过期时间。 这种反射能力允许Redis客户端检查指定key在数据集里面剩余的有效期。
     *
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 如果删除的key不存在，则直接忽略。
     *
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 从 key 指定的哈希集中移除指定的域
     *
     * @param key
     * @param item
     * @return
     */
    Long hdel(String key, String item);

    /**
     * @param key
     * @param val
     * @param expire
     * @return
     */


    Long setNX(String key, String val, Integer expire);


}
