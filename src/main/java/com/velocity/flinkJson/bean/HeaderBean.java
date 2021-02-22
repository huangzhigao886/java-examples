package com.velocity.flinkJson.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/30
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeaderBean {

    //任务名
    private String jobName;
    //任务类型
    private String jobType;
    //保存点间隔
    private int checkPointInterval;

    //数据处理异常时策略枚举类
    private String onErrorPolicy;
}
