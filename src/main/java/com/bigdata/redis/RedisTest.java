package com.bigdata.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * @description: 连接redis
 * @author: hzg
 * @create: 2019-09-22 22:20
 **/

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.100",6379);
        String ping = jedis.ping();
        System.out.println(ping);
        //向redis插入key
        jedis.set("k1","123");
        //获取key值
        String k1 = jedis.get("k1");
        jedis.set("k2","aaa");
        //获取redis所有的key
        Set<String> keys = jedis.keys("*");
        for(String key:keys){
            System.out.println(key);
        }
        System.out.println(k1);

        jedis.close();
    }


    /**
     * redis的事务操作
     * @param jedis
     */
    public static void testTransaction(Jedis jedis){
        jedis.flushDB();
        Transaction multi = jedis.multi();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","kuangshen");
        String result = jsonObject.toString();
        try {
            multi.set("user1",result);
            multi.set("user2",result);
            int i = 1/0;
            multi.exec();
        }catch (Exception e){
            //任务失败放弃事务操作
            multi.discard();
        }finally {
            jedis.close();
        }
    }


}
