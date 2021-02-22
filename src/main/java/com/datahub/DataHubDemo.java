package com.datahub;

import com.aliyun.datahub.client.exception.AuthorizationFailureException;
import com.aliyun.datahub.client.exception.DatahubClientException;
import com.aliyun.datahub.client.exception.InvalidParameterException;
import com.aliyun.datahub.client.exception.MalformedRecordException;
import com.aliyun.datahub.client.exception.NoPermissionException;
import com.aliyun.datahub.client.exception.ShardNotFoundException;
import com.aliyun.datahub.client.model.Field;
import com.aliyun.datahub.client.model.FieldType;
import com.aliyun.datahub.client.model.RecordEntry;
import com.aliyun.datahub.client.model.RecordSchema;
import com.aliyun.datahub.client.model.TupleRecordData;
import com.aliyun.datahub.clientlibrary.config.ProducerConfig;
import com.aliyun.datahub.clientlibrary.producer.Producer;
import com.aliyun.datahub.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhigao
 * @Date: 2020/6/29
 * @Description:
 */
public class DataHubDemo {

    private static final Logger LOG = LoggerFactory.getLogger(DataHubDemo.class);

    private static void sleep(long milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            // TODO:自行处理异常
        }
    }

    private static List<RecordEntry> genRecords(RecordSchema schema) {
        List<RecordEntry> recordEntries = new ArrayList<>();
        for (int cnt = 0; cnt < 10; ++cnt) {
            RecordEntry entry = new RecordEntry();
            entry.addAttribute("demo", "xiaohuang");
            TupleRecordData data = new TupleRecordData(schema);
            data.setField("name", "huang" + cnt);
            data.setField("id", ""+cnt);
            if (cnt % 2 == 0) {
                data.setField("sex", "男");
            } else {
                data.setField("sex", "女");
            }
            entry.setRecordData(data);
            recordEntries.add(entry);
        }
        return recordEntries;
    }

    private static void sendRecords(Producer producer, List<RecordEntry> recordEntries) {
        int maxRetry = 3;
        while (true) {
            try {
                // 自动选择shard写入
                producer.send(recordEntries, maxRetry);
                // 指定写入shard "0"
                // producer.send(recordEntries, "0", maxRetry);
                LOG.error("send records: {}", recordEntries.size());
                break;
            } catch (MalformedRecordException e) {
                // record 格式非法，根据业务场景选择忽略或直接抛异常
                LOG.error("write fail", e);
                throw e;
            } catch (InvalidParameterException |
                    AuthorizationFailureException |
                    NoPermissionException e) {
                // 请求参数非法
                // 签名不正确
                // 没有权限
                LOG.error("write fail", e);
                throw e;
            } catch (ShardNotFoundException e) {
                // shard 不存在, 如果不是写入自己指定的shard，可以不用处理
                LOG.error("write fail", e);
                sleep(1000);
            } catch (ResourceNotFoundException e) {
                // project, topic 或 shard 不存在
                LOG.error("write fail", e);
                throw e;
            } catch (DatahubClientException e) {
                // 基类异常，包含网络问题等，可以选择重试
                LOG.error("write fail", e);
                sleep(1000);
            }
        }
    }

    public static void main(String[] args) {
        // Endpoint以Region: 华东1为例，其他Region请按实际情况填写
        String endpoint = "https://dh-cn-hangzhou.aliyuncs.com";
        String accessId = "LTAI4G45czpfxqLxzo6VHs8h";
        String accessKey = "Hm4O1Mu2MK5wikrimQ9yRVaqfE8KCI";
        String projectName = "hzg_test";
        String topicName = "topic1";
        RecordSchema schema = new RecordSchema();
        schema.addField(new Field("id", FieldType.STRING));
        schema.addField(new Field("name", FieldType.STRING));
        schema.addField(new Field("sex", FieldType.STRING));
        ProducerConfig config = new ProducerConfig(endpoint, accessId, accessKey);
        Producer producer = new Producer(projectName, topicName, config);
        // 根据场景控制循环
        try {
            List<RecordEntry> recordEntries = genRecords(schema);
            sendRecords(producer, recordEntries);

        } finally {
            // 确保资源正确释放
            producer.close();
        }
    }
}

