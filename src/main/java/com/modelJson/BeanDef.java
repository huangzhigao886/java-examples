package com.modelJson;

import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description:
 */
@Data
public class BeanDef {
    /**
     * 每个节点的唯一ID
     */
    private String id;

    /**
     * source,sink,operator的并行度
     */
    private int parallelism = 1;
}
