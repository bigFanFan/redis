package com.hufan.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 事务加锁
 *
 * @author hufan created in 2019/1/24 14:11
 **/
public class TestTX {
    public static void main(String[] args) throws InterruptedException {
        TestTX testTX = new TestTX();
        boolean retValue = testTX.transMethod();
        System.out.println("main retValue:" + retValue);
    }

    private boolean transMethod() throws InterruptedException {
        Jedis jedis = new Jedis("172.16.10.196", 6379);
        // 余额欠款初始化
        // jedis.set("balance", "100");
        // jedis.set("debt", "0");
        int balance; // 可用余额
        int debt; // 欠款
        int amtToSubtract = 10; // 实刷金额
        jedis.watch("balance");
        // 模拟其他程序修改
        // jedis.set("balance", "5");
        // 休眠
        Thread.sleep(7000);
        balance = Integer.parseInt(jedis.get("balance"));
        if (balance < amtToSubtract) {
            jedis.unwatch();
            System.out.println("已经被修改");
            return false;
        } else {
            System.out.println("*************  transaction  *************");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            System.out.println("balance:" + balance);
            System.out.println("debt:" + debt);
            return true;
        }
    }
}