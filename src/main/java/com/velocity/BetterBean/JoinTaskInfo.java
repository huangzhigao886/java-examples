package com.velocity.BetterBean;


import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinTaskInfo {

    //job名称
    private String jobName;

    //job类型
    private String jobType;

    //是否记录保存点
    private boolean sourceSerial;

    //是否复写
    private boolean hdfsOverwrite;

    //redis的集群主机IP
    private String redisHosts;

    //字段信息列表
    private List<JoinSchemaBean> schemaList;

    //jdbc参数列表
    private List<JoinBeanDef> jdbcSourceParamList;

    //source源信息
    private List<JoinBeanDef> sourceList;

    //join操作信息
    private List<JoinBeanDef> joinOperatorList;

    //map操作信息
   private JoinMapOperatorBean joinMapOperatorBean;

    //sink信息
    private List<JoinBeanDef> sinkList;

    //流信息
    private List<JoinStreamDef> streamList;


    private List<Object> test;

}
