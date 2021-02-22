package com.modelJson;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description: 模型的详细信息
 */

import lombok.Data;

import java.util.List;

@Data
public class ModelBean {

    /**
     * 模型名称,唯一性
     */
    private String modelName;

    /**
     * 保存临时结果状态的目录集
     */
    private String tmpStatus;

    /**
     * 保存临时结果数据的目录集
     */
    private String tmpRes;


    /**
     * 是否为写入保存点，周期任务是为true,流式任务也为true
     */
    private String scheduleType;


    /**
     * 运行类型
     */
    private String runType;


    /**
     * 算子操作集
     */
    private List<OperatorBean> operators;

}
