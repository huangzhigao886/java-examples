package com.datahub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/2
 * @Description:
 */
public class DatahubComsumer1 {
    public static void main(String[] args) throws InterruptedException {
        String shardId = "0";
        String endpoint = "http://dh-cn-hangzhou.aliyuncs.com";
        String accessId = "accessId";
        String accessKey = "accessKey";
        String projectName = "hzg_test";
        String topicName = "topic1";
        DatahubClient datahubClient = DatahubClientBuilder.newBuilder()
                .setDatahubConfig(
                        new DatahubConfig(endpoint,
                                // 是否开启二进制传输，服务端2.12版本开始支持
                                new AliyunAccount(accessId, accessKey), true))
                //专有云使用出错尝试将参数设置为           false
                // HttpConfig可不设置，不设置时采用默认值
                .setHttpConfig(new HttpConfig()
                        .setCompressType(HttpConfig.CompressType.LZ4) // 读写数据推荐打开网络传输 LZ4压缩
                        .setConnTimeout(10000))
                .build();
        String cursor = datahubClient.getCursor(projectName, topicName, shardId, CursorType.OLDEST).getCursor();
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("id", FieldType.STRING));
        schema.addField(new Field("name", FieldType.STRING));
        schema.addField(new Field("sex", FieldType.STRING));
        List<ShardEntry> shards = datahubClient.listShard(projectName, topicName).getShards();
        while (true) {
            GetRecordsResult result = datahubClient.getRecords(projectName, topicName, shardId, schema, cursor, 1000);
            if (result.getRecordCount() <= 0) {
                // 无数据，sleep后读取
                Thread.sleep(10000);
                continue;
            }
            for (RecordEntry entry : result.getRecords()) {
                TupleRecordData data = (TupleRecordData) entry.getRecordData();
                Object id = data.getField("id");
                Object name = data.getField("name");
                Object sex = data.getField("sex");
                System.out.println(data);
            }
            cursor = result.getNextCursor();
        }
    }
}
