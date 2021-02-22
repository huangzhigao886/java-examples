package com.thread;

import cn.hutool.core.thread.ExecutorBuilder;
import com.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: huangzhigao
 * @Date: 2020/8/31
 * @Description:
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        executorService.
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .setWorkQueue(new LinkedBlockingQueue<>(100))
                .build();
        List<RequestInfo> res = new ArrayList<>();


        res.add(new RequestInfo());
        res.add(new RequestInfo());
        res.add(new RequestInfo());
        res.add(new RequestInfo());
        res.add(new RequestInfo());
        res.add(new RequestInfo());

        long currentTimeMillis = System.currentTimeMillis();
        List<Future<Object>> futures = executor.invokeAll(res);
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
        String ress = "";
        for (Future<Object> result : futures) {
            try {
                Object o = result.get();
                System.out.println(o);

            } catch (Exception e) {
                throw new Exception(e);
            } finally {
                executor.shutdown();
            }
        }
        executor.shutdown();


    }



}
