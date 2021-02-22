package com.modelJson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description: 模型组件的头部
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeaderBean {

    /**
     * 任务名
     */
    private String jobName;

    /**
     * 任务类型
     */
    private String jobType;

    /**
     * 保存点周期
     */
    private String checkPointInterval;

    /**
     * 错误机制
     */
    private String onErrorPolicy;
}
