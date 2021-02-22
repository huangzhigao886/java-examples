package com.modelJson;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description: 算子类型表对象
 */
@Data
public class OperatorBean {

    /**
     * 算子的名称
     */
    private String operatorName;

    /**
     * 算子的类型    SINK,SOURCE,OPERATOR_MAP,OPERATOR_FILTER
     */
    private String operatorType;


    /**
     * 输入字段
     */
    private List<DataField> inputSchema;

    /**
     * 输出字段
     */
    private List<DataField> outputSchema;

    /**
     * 来源
     */
    private List<String> from;

    /**
     * 输出
     */
    private List<String> to;

    /**
     * 连接信息
     */
    private Map<String, Object> connectionsInfo;


    /**
     * 每个算子的信息
     */
    private List<Map<String, Object>> params;

}
