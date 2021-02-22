package com.velocity.flinkJson.bean;

import java.util.List;

/**
 * bean配置方法定义描述类
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class ConfigMethodDef {
    /**
     * 方法名称
     */
    private String name;
    /**
     * 参数列表
     */
    private List<Object> args;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }
}
