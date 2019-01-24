package com.hufan.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 池子测试
 * @author hufan created in 2019/1/24 15:19
 **/
public class TestPool {
    public static void main(String[] args) {
        JedisPool jedisPool1 = JedisPoolUtil.getJedisPoolInstance();
//        JedisPool jedisPool2 = JedisPoolUtil.getJedisPoolInstance();
//        System.out.println(jedisPool1 == jedisPool2);
        Jedis jedis = null;
        try {
            jedis = jedisPool1.getResource();
            jedis.set("aa", "bb");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.release(jedisPool1, jedis);
            System.out.println(jedis.get("aa"));
        }
    }
}