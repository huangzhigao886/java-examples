package com.velocity.flinkJson.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/30
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigBean {

    //保存点存储方式，目前存储到redis
    private String storetype;

    //Prop文件存储路径
    private String propPath;

    //是否覆盖写入，周期全量，一次性都是true,其他为false
    private String overwrite;

    //是否记录保存点，只有周期增量需要为true,流式任务也需要记录保存点
    private String sourceSerial;


    //缓存类型，主要运用于规则转换，规则在redis中
    private String cacheType;
}
