package com.bigdata.es;

import cn.hutool.core.util.RandomUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: huangzhigao
 * @Date: 2020/8/12
 * @Description:
 */
public class EsClient1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("url.151.141", 9200, "http"))
        );
        bulkProcess(client);
        client.close();
    }



    public static void bulkProcess(RestHighLevelClient client) throws InterruptedException {
        BulkProcessor.Builder builder = BulkProcessor.builder(client::bulkAsync, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {

            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
                System.out.println("报错了");

            }
        });
        builder.setFlushInterval(TimeValue.timeValueMinutes(2));
        builder.setBulkSize(new ByteSizeValue(10, ByteSizeUnit.MB));
        builder.setBulkActions(1000);
        BulkProcessor build1 = builder.build();

        for(int i =0;i<100;i++){
            Map<String, String> map = new HashMap<>();
            if(i %2 == 0){
                map.put("mmm","mm"+i);
            }
            build1.add(new UpdateRequest("hh1", "doc","00000"+i).doc(map).upsert(map));
        }
        build1.awaitClose(10, TimeUnit.SECONDS);
        build1.close();
    }
}
