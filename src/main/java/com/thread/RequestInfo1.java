package com.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.Callable;

/**
 * @Auther: huangzhigao
 * @Date: 2020/12/1
 * @Description:
 */
public class RequestInfo1 implements Callable<Object> {
    @Override
    public Object call() throws Exception {

        return 1/0;
    }
}
