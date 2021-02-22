package com.validate;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/9
 * @Description: 节点描述类
 */
@Data
public class Vertex {
    /**
     * 节点名称
     */
    private String vertexName;

    /**
     * 节点类型 目前分为3种，source,sink,operator
     */
    private String vertexType;

    /**
     * 节点输入,元素为上个节点的集合
     */
    private List<String> from;

    /**
     * 节点输出,元素为下个节点的集合
     */
    private List<String> to;

    /**
     * 算子的操作类型类型维护 关联类
     */
    private String operatorType;

    /**
     * 输入的schema
     */
    public List<String> inputSchema;

    /**
     * 输出的schema
     */
    public List<String> outputSchema;
    /**
     * 规则Schema
     */
    private List<String> ruleSchema;
}
