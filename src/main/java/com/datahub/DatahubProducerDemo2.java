package com.datahub;

import com.aliyun.datahub.client.DatahubClient;
import com.aliyun.datahub.client.DatahubClientBuilder;
import com.aliyun.datahub.client.auth.AliyunAccount;
import com.aliyun.datahub.client.common.DatahubConfig;
import com.aliyun.datahub.client.http.HttpConfig;
import com.aliyun.datahub.client.model.ListProjectResult;
import com.aliyun.datahub.client.model.RecordEntry;
import com.aliyun.datahub.client.model.RecordSchema;
import com.aliyun.datahub.client.model.TupleRecordData;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/1
 * @Description:
 */
public class DatahubProducerDemo2 {
    public static void main(String[] args) {

        String endpoint = "https://dh-cn-hangzhou.aliyuncs.com";
        String accessId = "LTAI4GAKxzi8iiTuAdW6Qjac";
        String accessKey = "1hgqKnbUf98OMUwfSsvx75WGmRg6Dh";
        String projectName = "ryantest";
        String topicName = "ryan";
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
//        datahubClient.createProject("hzgDatahub1","测试连接datahub");
//        ListProjectResult listProjectResult = datahubClient.listProject();
//        for (String str : listProjectResult.getProjectNames()){
//            System.out.println(str);
//        }


        RecordSchema recordSchema = datahubClient.getTopic(projectName, topicName).getRecordSchema();
        // 生成十条数据
        List<RecordEntry> recordEntries = new ArrayList<>();
        for (int i = 50; i < 60; ++i) {
            RecordEntry recordEntry = new RecordEntry();
            // 对每条数据设置额外属性，例如ip 机器名等。可以不设置额外属性，不影响数据写入
            recordEntry.addAttribute("key1", "value1");
            TupleRecordData data = new TupleRecordData(recordSchema);
            data.setField("a1", "HelloWorld");
            data.setField("a2", "" + i);
            recordEntry.setRecordData(data);
            recordEntries.add(recordEntry);
        }
        datahubClient.putRecords(projectName, topicName, recordEntries);
        System.out.println("write data successful");
    }
}
