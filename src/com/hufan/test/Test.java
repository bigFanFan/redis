package com.hufan.test;

import redis.clients.jedis.Jedis;

/**
 * 连接
 * @author hufan created in 2019/1/24 11:32
 **/
public class Test {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.10.196", 6379);
        System.out.println(jedis.ping());
    }
}