package com.model;

/**
 * 导入其他配置文件定义描述类
 *
 * @auth: lidesheng
 * @since DataEngine 1.0
 */
public class IncludeDef {
	/** 是否为资源引用方式 */
    private boolean resource = false;
    /** 是否为覆盖当前配置文件 */
    boolean override = false;
    /** 导入文件路径 */
    private String file;

    public boolean isResource() {
        return resource;
    }

    public void setResource(boolean resource) {
        this.resource = resource;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isOverride() {
        return override;
    }

    public void setOverride(boolean override) {
        this.override = override;
    }
}
