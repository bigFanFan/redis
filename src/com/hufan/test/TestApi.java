package com.hufan.test;

import redis.clients.jedis.Jedis;

/**
 * 五大类型测试
 *
 * @author hufan created in 2019/1/24 11:50
 **/
public class TestApi {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.10.196", 6379);
        // String 类型
        jedis.set("k1", "v1");
        jedis.set("k2", "v2");
        jedis.set("k3", "v3");
        jedis.set("k4", "2");
        jedis.incrBy("k4", 3);
        System.out.println(jedis.get("k4"));

        // Hash 类型

        // List 类型
        jedis.lpush("list01", "1", "2", "3", "4", "5", "a");
        jedis.rpush("list02", "1", "2", "3", "4", "5", "c");
        System.out.println("输入list01的元素：" + jedis.lrange("list01", 0, -1));
        System.out.println("输入list02的元素：" + jedis.lrange("list02", 0, -1));
        jedis.lpop("list01");
        jedis.lpop("list02");
        System.out.println("list01第2个元素：" + jedis.lindex("list01", 2));
        System.out.println("list02第2个元素：" + jedis.lindex("list02", 2));
        System.out.println("list01的长度" + jedis.llen("list01"));
        System.out.println("list02的长度" +jedis.llen("list02"));

        // Set 类型
        jedis.sadd("set01", "1", "1", "2", "3", "4", "a");
        jedis.sadd("set02", "1", "1", "2", "3", "4", "c");
        System.out.println("取出set01的值:" + jedis.smembers("set01"));
        System.out.println("set01中是否存在1这个值" + jedis.sismember("set01", "1"));
        System.out.println("集合中元素个数：" + jedis.scard("set01"));
        System.out.println("随机出2个整数" + jedis.srandmember("key01"));
        System.out.println("差集" + jedis.sdiff("set01", "set02"));
        System.out.println("交集" + jedis.sinter("set01", "set02"));
        System.out.println("并集" + jedis.sunion("set01", "set02"));

        // Zset 类型

    }
}