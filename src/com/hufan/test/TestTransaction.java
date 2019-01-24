package com.hufan.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 事务操作
 *
 * @author hufan created in 2019/1/24 12:28
 **/
public class TestTransaction {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.10.196", 6379);
        Transaction transaction = jedis.multi();
        transaction.set("k6", "v66");
        transaction.set("k7", "v77");
//        transaction.exec();
        transaction.discard();
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.get("k6"));
    }
}