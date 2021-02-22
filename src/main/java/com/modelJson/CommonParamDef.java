package com.modelJson;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description: 通用参数类，参数都为String类型
 */
@Data
public class CommonParamDef extends Vertex {
    /**
     * JDBC的连接参数信息
     */
    private List<Object> jdbcArgs;


}
