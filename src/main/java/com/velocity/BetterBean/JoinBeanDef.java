package com.velocity.BetterBean;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinBeanDef {
    private String id;
    private String className;
    private List<Object> constructArgsList;

    private JoinConstructMethodDef joinConstructMethodDef;

    //这个暂时不用
    private List<JoinConstructMethodDef> configMethodsList;
}
