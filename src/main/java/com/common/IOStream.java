package com.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/25
 * @Description: IO stream流
 */
@Slf4j
public class IOStream {
    public static void main(String[] args) {
        BufferedInputStream in = FileUtil.getInputStream("D://11.yaml");
        BufferedOutputStream out = FileUtil.getOutputStream("D://11copy.yaml");
        long copySize = IoUtil.copy(in, out, 1024);

//
        Properties properties = new Properties();
//        try {
//            properties.load(resource.getStream());
//        } catch (IOException e) {
//            log.error("加载配置失败");
//        }
        Console.log("properties:{}",properties);
    }
}
