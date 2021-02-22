package com.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.Callable;

/**
 * @Auther: huangzhigao
 * @Date: 2020/12/1
 * @Description:
 */
public class RequestInfo implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        int res = RandomUtil.randomInt(3, 4);
        if (res == 3) {
            throw new Exception("error");
        }
        Thread.sleep(2000);

        return res;
    }
}
