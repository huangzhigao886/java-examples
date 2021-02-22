package com.modelJson;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description: 通用组件的bean
 */
@Data
public class ComponentDef {

    /**
     * 上下文的字段信息
     */
    private List<SchemaDef> schemaList;

    /**
     * 通用的连接参数
     */
    private List<CommonParamDef> commonParamList;

}
