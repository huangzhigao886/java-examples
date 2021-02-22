package com.bigdata.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/19
 * @Description:
 */
public class RedisClient {
    public static void main(String[] args) {
        String redisHost = "url:7001,url:7002,url:7001,url:7002,url:7001,url:7002";
        Set<HostAndPort> hostAddresses = new HashSet<>();
        String[] addresses = redisHost.split(",");
        for (String address : addresses) {
            String[] split = address.split(":");
            hostAddresses.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        JedisCluster jedisCluster = new JedisCluster(hostAddresses);

        /**
         * 赋值
         */
//        Map<String, String> externalDicsMap = new HashMap<>();
//        externalDicsMap.put("externalDic_sexTable", "性别表");
//        externalDicsMap.put("externalDic_xingTable", "姓名表");
//        externalDicsMap.put("externalDic_provinceTable", "省份表");
//        jedisCluster.hset("externalDic", externalDicsMap);
//        buildSexValue(jedisCluster);
//        buildProvinceVaue(jedisCluster);
//        buildXingValue(jedisCluster);
        Map<String, String> map1 = jedisCluster.hgetAll("METRIC:NAMESPACE");
        for(Map.Entry<String,String> entry:map1.entrySet()){
            jedisCluster.hdel("METRIC:NAMESPACE",entry.getKey());
        }

    }

    @Test
    public void test(){
        String redisHost = "url.146.121:7001,url.146.121:7002,url.146.122:7001,url.146.122:7002,url:7001,url:7002";
        Set<HostAndPort> hostAddresses = new HashSet<>();
        String[] addresses = redisHost.split(",");
        for (String address : addresses) {
            String[] split = address.split(":");
            hostAddresses.add(new HostAndPort(split[0], Integer.parseInt(split[1])));
        }
        JedisCluster jedisCluster = new JedisCluster(hostAddresses);
        String s = jedisCluster.get("BIGDATA:ASSET:MXBQ");
        JSONArray array = JSON.parseArray(s);
        System.out.println("aaa");
    }


    public static void buildSexValue(JedisCluster jedisCluster) {
        Map<String, String> map = new HashMap<>();
        map.put("男", "male");
        map.put("女", "female");
        map.put("中性", "noSex");
        jedisCluster.hset("externalDic_sexTable", map);
    }

    public static void buildXingValue(JedisCluster jedisCluster) {
        Map<String, String> map = new HashMap<>();
        map.put("赵", "zhao");
        map.put("钱", "qian");
        map.put("孙", "sun");
        map.put("李", "li");
        map.put("王", "wang");
        jedisCluster.hset("externalDic_xingTable", map);
    }

    public static void buildProvinceVaue(JedisCluster jedisCluster) {
        Map<String, String> map = new HashMap<>();
        map.put("北京", "京");
        map.put("上海", "沪");
        map.put("深圳", "深");
        map.put("天津", "津");
        jedisCluster.hset("externalDic_provinceTable", map);
    }

}
