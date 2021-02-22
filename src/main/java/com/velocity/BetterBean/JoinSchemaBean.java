package com.velocity.BetterBean;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinSchemaBean {
    private String id;
    private String className;
    private List<JoinDataField> dataFieldList;
}
