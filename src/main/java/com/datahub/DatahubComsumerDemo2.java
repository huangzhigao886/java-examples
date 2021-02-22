package com.datahub;

import com.aliyun.datahub.client.model.*;
import com.aliyun.datahub.clientlibrary.config.ConsumerConfig;
import com.aliyun.datahub.clientlibrary.consumer.Consumer;

import java.util.List;


/**
 * @Auther: huangzhigao
 * @Date: 2020/7/1
 * @Description:
 */
public class DatahubComsumerDemo2 {
    public static void main(String[] args) {
        String endpoint = "endPoint";
        String accessId = "accessId";
        String accessKey = "accessKey";
        String projectName = "projectName";
        String topicName = "topicName";
        String subId = "subId";
        ConsumerConfig config = new ConsumerConfig(endpoint, accessId, accessKey);
        Consumer consumer = new Consumer(projectName, topicName, subId, config);
        while (true) {
            RecordEntry recordEntry = consumer.read(3);
            if (recordEntry != null) {
                TupleRecordData blobData = (TupleRecordData) recordEntry.getRecordData();
                RecordSchema recordSchema = blobData.getRecordSchema();
                List<Field> fields = recordSchema.getFields();
                for (Field field : fields) {
                    System.out.print(blobData.getField(field.getName()) + "=====");
                }
                System.out.println();
//                Object name = blobData.getField("name");
//                Object id = blobData.getField("id");
//                Object sex = blobData.getField("sex");
//                System.out.println(name + " , " + id + " , " + sex);
            }
        }
    }
}
