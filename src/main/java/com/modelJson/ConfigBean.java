package com.modelJson;

import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/15
 * @Description:
 */
@Data
public class ConfigBean {
    /**
     * 保存点存储类型
     */
    private String storeType;

    /**
     * 是否覆盖写入 ，周期/流式为false
     */
    private String overwrite;

    /**
     * 是否记录保存点
     */
    private String sourceSerial;

    /**
     * 缓存类型
     */
    private String cacheType;


}
