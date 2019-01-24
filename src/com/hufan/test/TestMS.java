package com.hufan.test;

import redis.clients.jedis.Jedis;

/**
 * 主从复制
 *
 * @author hufan created in 2019/1/24 14:39
 **/
public class TestMS {
    public static void main(String[] args) {
        Jedis jedisMaster = new Jedis("172.16.10.196", 6379);
        Jedis jedisSlave = new Jedis("172.16.10.196", 6380);

        jedisSlave.slaveof("172.16.10.196", 6379);
        jedisMaster.set("class", "11221");
        System.out.println(jedisSlave.get("class"));

    }
}