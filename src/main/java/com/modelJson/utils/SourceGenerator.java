package com.modelJson.utils;

import com.modelJson.ComponentDef;
import com.modelJson.OperatorBean;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description: 生成Source源
 */
public class SourceGenerator {

    /**
     * source信息
     */
    private List<OperatorBean> sourceList;

    /**
     * 通用组件信息
     */
    private List<ComponentDef> componentDefList;

    /**
     * 构造函数
     *
     * @param sourceList source的信息
     */
    public SourceGenerator(List<OperatorBean> sourceList, List<ComponentDef> componentDefList) {
        this.sourceList = sourceList;
        this.componentDefList = componentDefList;
    }


    /**
     * 添加字段到component
     */
    private void addSchema() {
        for (OperatorBean operatorBean : sourceList) {

        }
    }

    public static void main(String[] args) {
        String aa = "12345";
        System.out.println(aa.contains("12"));
        System.out.println(aa.contains("34"));
        System.out.println(aa.contains("35"));
    }


}
