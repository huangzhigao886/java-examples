package com.validate.valid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/13
 * @Description: 常量工具类
 */
public interface UtilConstants {


    public static final List<String> changeStructureOperaList = Stream.of("添加字段", "选择列", "数据聚合", "脚本", "聚合", "左连接", "交集", "并集", "差集")
            .collect(Collectors.toList());
    public static final List<String> NotChangeStructureOperaList = Stream.of("去重", "排序", "过滤")
            .collect(Collectors.toList());

    public static List<String> relationOperatorList = Stream.of("差集", "交集", "并集", "左连接")
            .collect(Collectors.toList());
    public static List<String> singleOperatorList = Stream.of("添加字段", "选择列", "数据聚合", "脚本", "聚合", "去重", "排序", "过滤")
            .collect(Collectors.toList());
}
