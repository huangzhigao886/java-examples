package com.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用对象定义，包含使用类名+构造参数+属性+初始化方法列表，类似于Spring的IoC注入。
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class ObjectDef {
    /**
     * 类名
     */
    private String className;
    /**
     * 构造参数列表
     */
    private List<Object> constructorArgs;

    /**
     * 配置方法列表
     */
    private List<ConfigMethodDef> configMethods;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Object> getConstructorArgs() {
        return constructorArgs;
    }

    /**
     * Sets the arguments for the constructor and checks for references.
     *
     * @param constructorArgs Constructor arguments
     */
    public void setConstructorArgs(List<Object> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public boolean hasConstructorArgs() {
        return this.constructorArgs != null && this.constructorArgs.size() > 0;
    }

    public List<ConfigMethodDef> getConfigMethods() {
        return configMethods;
    }

    public void setConfigMethods(List<ConfigMethodDef> configMethods) {
        this.configMethods = configMethods;
    }

}
