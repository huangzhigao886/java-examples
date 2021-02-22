package com.velocity.Bean;

import lombok.Data;

import java.util.Map;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class MapOperatorBean {
    private String id;
    private String className;
    private Map<String,String> constructArgsMap;
}
