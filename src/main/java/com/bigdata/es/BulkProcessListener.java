package com.bigdata.es;

import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;

/**
 * @Auther: huangzhigao
 * @Date: 2020/9/25
 * @Description:
 */
public class BulkProcessListener implements BulkProcessor.Listener {

    @Override
    public void beforeBulk(long executionId, BulkRequest request) {
        System.out.println("在执行bulk之前");
    }

    @Override
    public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
        //在执行bulk成功后
        System.out.println("在执行bulk成功后");
    }

    @Override
    public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
        System.out.println("执行失败");
    }
}
