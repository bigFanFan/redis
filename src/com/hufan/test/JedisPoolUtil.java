package com.hufan.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 池子工具
 * @author hufan created in 2019/1/24 15:00
 **/
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    // 构造方法私有化
    private JedisPoolUtil() {

    }

    // 获得实例
    public static JedisPool getJedisPoolInstance() {
        if (null == jedisPool) {
            synchronized (JedisPoolUtil.class) {
                if (null == jedisPool) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxActive(1000);
                    jedisPoolConfig.setMaxIdle(32);
                    jedisPoolConfig.setMaxWait(100 * 1000);
                    jedisPoolConfig.setTestOnBorrow(true);
                    jedisPool = new JedisPool(jedisPoolConfig, "172.16.10.196", 6379);
                }
            }
        }
        return jedisPool;
    }

    // 返回实例到池子
    public static void release(JedisPool jedisPool, Jedis jedis) {
        if (null != jedisPool) {
            jedisPool.returnResource(jedis);
        }
    }
}