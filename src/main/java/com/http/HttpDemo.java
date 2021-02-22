package com.http;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Auther: huangzhigao
 * @Date: 2020/2/20
 * @Description:
 */
public class HttpDemo {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //2.创建post请求方式实例
        HttpPost httpPost=new HttpPost("http://url.151.141:29606/rest/post");

        //2.1设置请求头 发送的是json数据格式
//        httpPost.setHeader("Content-type", "application/json;charset=utf-8");
//        httpPost.setHeader("Connection", "Close");

        String str = "{\n" +
                "  \"start\":\"2020-02-20 17:43:44\",\n" +
                "  \"end\":\"2020-02-20 18:43:44\"\n" +
                "}";
        StringEntity entity = new StringEntity(str.toString(), Charset.forName("UTF-8"));
//        entity.setContentEncoding("UTF-8");  //设置编码格式
//        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        //把请求消息实体塞进去
        httpPost.setEntity(entity);

        //4.执行http的post请求
        CloseableHttpResponse httpResponse=null;
        httpResponse=httpClient.execute(httpPost);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
    }

}
