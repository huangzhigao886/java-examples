package com.datahub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.ShardEntry;
import com.aliyun.datahub.model.ShardState;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/6
 * @Description:
 */
public class Datahub1 {
    public static void main(String[] args) {
        String projectName= "ryantest";
        String topic="ryan";
        String subId = "1594014659080UBUBO";
        byte[] bytes = Base64.getDecoder().decode("pass");
        String key =  new String(bytes);
        DatahubClient datahubClient = DatahubClientBuilder.newBuilder()
                .setDatahubConfig(
                        new DatahubConfig("https://dh-cn-hangzhou.aliyuncs.com",
                                // 是否开启二进制传输，服务端2.12版本开始支持
                                new AliyunAccount("key", key), false))
                //专有云使用出错尝试将参数设置为           false
                // HttpConfig可不设置，不设置时采用默认值
                .setHttpConfig(new HttpConfig()
                        .setCompressType(HttpConfig.CompressType.LZ4) // 读写数据推荐打开网络传输 LZ4压缩
                        .setConnTimeout(10000))
                .build();

        List<ShardEntry> shards = datahubClient.listShard("ryantest", "ryan").getShards();
        List<String> aliveShard = new ArrayList<>();
        for (ShardEntry shardEntry : shards) {
            if (!ShardState.CLOSED.equals(shardEntry.getState())) {
                aliveShard.add(shardEntry.getShardId());
            }
        }
        datahubClient.openSubscriptionSession(projectName, topic, subId, aliveShard);
        System.out.println("a");
    }
}
