package com.datahub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.*;
import com.util.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/8
 * @Description:
 */
public class DatahubDemo2 {
    public static void main(String[] args) {
        String endpoint = "https://dh-cn-hangzhou.aliyuncs.com";
        String accessId = "accessId";
        String accessKey = "accessKey";
        String projectName = "ryantest";
        String topic = "ryan";
        String subId = "15949668993925NCK3";
        List<String> list = new ArrayList<>();
        list.add("0");
        String key = CommonUtils.base64Decode(accessKey);
        DatahubClient datahubClient = DatahubClientBuilder.newBuilder()
                .setDatahubConfig(
                        new DatahubConfig(endpoint,
                                // 是否开启二进制传输，服务端2.12版本开始支持
                                new AliyunAccount(accessId, key), false))
                //专有云使用出错尝试将参数设置为           false
                // HttpConfig可不设置，不设置时采用默认值
                .setHttpConfig(new HttpConfig()
                        .setCompressType(HttpConfig.CompressType.LZ4) // 读写数据推荐打开网络传输 LZ4压缩
                        .setConnTimeout(10000))
                .build();
        RecordSchema schema = new RecordSchema();
        String cursor = datahubClient.getCursor(projectName, topic, "0", CursorType.OLDEST).getCursor();
        schema.addField(new Field("a1",FieldType.STRING));
        schema.addField(new Field("a2",FieldType.STRING));

        OpenSubscriptionSessionResult openSubscriptionSessionResul = datahubClient.openSubscriptionSession(projectName, topic, subId, list);
        GetRecordsResult records = datahubClient.getRecords(projectName, topic, "0", schema, cursor, 1000);
        for (int i = 0; i < records.getRecords().size(); i++) {
            TupleRecordData data = (TupleRecordData) records.getRecords().get(i).getRecordData();
            Object code1 = data.getField("a1");
            Object code2 = data.getField("a1");
            System.out.println(code1);
            System.out.println(code2);
        }

    }
}
