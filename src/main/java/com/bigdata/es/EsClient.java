package com.bigdata.es;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
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

import static org.reflections.util.ConfigurationBuilder.build;

/**
 * @Auther: huangzhigao
 * @Date: 2020/8/12
 * @Description:
 */
public class EsClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("url", 9200, "http"))
        );
        bulkProcess(client);
        client.close();
    }


    public static void deleteIndex(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        //超时时间2分钟
        request.timeout("2m");
        //连接主节点的超时时间
        request.masterNodeTimeout("1m");
    }

    public static void createIndex(String index, String id, RestHighLevelClient client) throws IOException {
        //index为索引，6.2版本一下，type只是"doc",id为文档的id，类似一行数据的主键
        IndexRequest indexRequest = new IndexRequest(index, "doc", id);
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        //插入数据的方式1：插入JSON,指定插入的数据类型为JSON
        indexRequest.source(json, XContentType.JSON);
        //同步方式
//        client.index(indexRequest);
        //异步方式
        client.indexAsync(indexRequest, new ActionListener<IndexResponse>() {
            int count = 0;

            @Override
            public void onResponse(IndexResponse indexResponse) {
                //成功时走这个
                count++;
                System.out.println("单独执行");
            }

            @Override
            public void onFailure(Exception e) {
                //失败走这个
            }
        });
        //插入的数据方式2：插入map的键值对形式
//        Map<String,String> datas = new HashMap<>();
//        indexRequest.source(datas);
        //插入的数据方式3: 构建一个builder
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject();
//        {
//            builder.field("user", "kimchy");
//            builder.field("postDate", new Date());
//            builder.field("message", "trying out Elasticsearch");
//        }
//        builder.endObject();
//        indexRequest.source(builder);
    }


    /**
     * 获取所有的doc
     */
    public static void getDoc(String index, RestHighLevelClient client) throws IOException {
        GetRequest doc = new GetRequest(index);
        GetResponse documentFields = client.get(doc);
        System.out.println(documentFields);
    }

    public static void deleteDoc(String index, RestHighLevelClient client) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        client.delete(deleteRequest);
    }

    public static void bulkRequest(String index, RestHighLevelClient client, String id, Map<String, String> values) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest("aa", "doc", "6")
                .source(XContentType.JSON, "aa", "foo"));
        bulkRequest.add(new IndexRequest("aa", "doc", "7")
                .source(XContentType.JSON, "field", "bar"));
        bulkRequest.add(new IndexRequest("aa", "doc", "8")
                .source(XContentType.JSON, "field", "baz"));
//        client.bulkAsync(bulkRequest, new ActionListener<BulkResponse>() {
//            int count;
//
//            @Override
//            public void onResponse(BulkResponse bulkItemResponses) {
//                count++;
//                System.out.println("执行了" + count + "次");
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
        client.bulk(bulkRequest);

//        client.bulk()
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
        Map<String, String> map = new HashMap<>();
        map.put("name", "eeer");
        map.put("sex","zzzz");

        for(int i =0;i<1;i++){
            build1.add(new IndexRequest("hzg008", "doc").id("hzg123").source(map));
        }
        long currentTimeMillis = System.currentTimeMillis();
        build1.awaitClose(10, TimeUnit.SECONDS);
        build1.close();
//        System.out.println("是否成功写"+b);
    }
}
