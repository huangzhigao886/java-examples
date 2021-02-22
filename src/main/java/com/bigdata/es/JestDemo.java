package com.bigdata.es;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/25
 * @Description:
 */
public class JestDemo {
    public static void main(String[] args) throws IOException {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig
                        .Builder("http://url:9200")
                        .multiThreaded(true)
                        //一个route 默认不超过2个连接  路由是指连接到某个远程注解的个数。总连接数=route个数 * defaultMaxTotalConnectionPerRoute
                        .defaultMaxTotalConnectionPerRoute(2)
                        //所有route连接总数
                        .maxTotalConnection(2)
                        .connTimeout(10000)
                        .readTimeout(10000)
                        .gson(new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create())
                        .build()
        );
        JestClient client = factory.getObject();
//        Search build = new Search.Builder(null).addIndex("hzg_12").addType("doc").build();
//        SearchResult execute = client.execute(build);
        DeleteByQuery build = new DeleteByQuery.Builder("null").addIndex("hzg_12").addType("doc").build();
        JestResult execute = client.execute(build);
        System.out.println("aa");
//        client.execute()
    }
}
