package com.velocity.BetterBean;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/19
 * @Description:
 */
@Data
public class JoinConstructMethodDef {
    //方法名
    private String name;
    //构造参数
    private List<Object> constructorArgs;

    private List<Object> pkFields;

    private String region;

    private String tableName;

}
