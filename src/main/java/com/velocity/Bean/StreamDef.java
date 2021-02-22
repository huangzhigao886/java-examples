package com.velocity.Bean;

import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class StreamDef {
    private String name;
    private String from;
    private String to;
    private String operation;
    private String joinSide;
    private String joinKeys;
    private String tableName;
}
