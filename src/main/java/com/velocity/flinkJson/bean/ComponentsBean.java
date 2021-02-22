package com.velocity.flinkJson.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2020/3/30
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentsBean {
    private String id;
    private String className;
    private List<Object> constructList;
    private List<Object> configMethodList;
}
