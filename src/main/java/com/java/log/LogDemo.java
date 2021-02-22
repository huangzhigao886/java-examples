package com.java.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/23
 * @Description:
 */
@Slf4j
public class LogDemo {
    public static void main(String[] args) {
        String name = "zhangsan";
        String address = "putian";
        log.debug("姓名：[{}],地址:[{}]",name,address);
    }
}
