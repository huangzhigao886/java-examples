package com.velocity.BetterBean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinMapOperatorBean {
    private String id;
    private String className;
    private Map<String, String> constructArgsMap;
}
