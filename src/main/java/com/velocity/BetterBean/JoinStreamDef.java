package com.velocity.BetterBean;

import lombok.Data;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinStreamDef {
    private String name;
    private String from;
    private String to;
    private String operation;
    private String joinSide;
    private String joinKeys;
    private String tableName;
}
