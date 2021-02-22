package com.bigdata.zookeeper;


import cn.hutool.core.thread.ExecutorBuilder;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @Auther: huangzhigao
 * @Date: 2020/12/15
 * @Description:
 */

public class CuratorDemo {

    public static CuratorFramework init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("url:2181,url:2181,url:2181"
                , 10000, 10000, retryPolicy);
        return curatorFramework;
    }

    @Test
    public void testConnect() throws Exception {
        CuratorFramework curatorFramework = init();
        curatorFramework.start();
        curatorFramework.create().forPath("/demo1");
        curatorFramework.create().forPath("/demo1/aa", "aa".getBytes());

//        //获取数据
//        byte[] bytes = curatorFramework.getData().forPath("/demo1/aa");
//        System.out.println(new String(bytes));
//        //更新数据
//        curatorFramework.setData().forPath("/demo1/aa","bb".getBytes());
//        //获取子节点列表
//        List<String> list = curatorFramework.getChildren().forPath("/");
        curatorFramework.close();
    }


    @Test
    public void testFlink() throws Exception {
        CuratorFramework curatorFramework = init();
        curatorFramework.start();
        curatorFramework.delete().forPath("/flink/default/leader/00e810dc108e9a9e0af8e1ee8661f2fe");
        curatorFramework.close();
    }


    @Test
    public void deleteNode() throws Exception {
        System.setProperty("jute.maxbuffer", "25368120");
        CuratorFramework curatorFramework = init();
        curatorFramework.start();
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(10)
                .setMaxPoolSize(20)
                .setWorkQueue(new LinkedBlockingQueue<>(2048))
                .build();
        Set<String> jobIdSet = new HashSet<>();
        jobIdSet.addAll(curatorFramework.getChildren().forPath("/flink/default/leader"));
        jobIdSet.addAll(curatorFramework.getChildren().forPath("/flink/default/leaderlatch"));
        System.out.println("任务是多少:" + jobIdSet.size());
        for (String jobId : jobIdSet) {
            String leaderNodeName = "/flink/default/leader" + "/" + jobId;
            String leaderlatchNodeName = "/flink/default/leaderlatch" + "/" + jobId;
            while (true) {
                try {
                    executor.submit(new ZkDeleteThread(curatorFramework, leaderNodeName, leaderlatchNodeName));
                    break;
                } catch (Exception e) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }

    }


    @Test
    public void createZkWatcher() {
        CuratorFramework curatorFramework = init();
        curatorFramework.start();
        curatorFramework.getData().watched();
//        curatorFramework.setData()
    }


    @Test
    public void testDelete() throws Exception {
        CuratorFramework init = init();
        init.start();
        if (init.checkExists().forPath("/demo1") != null) {
            init.delete().deletingChildrenIfNeeded().forPath("/demo1");
        }
        init.close();

    }
}
