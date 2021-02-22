package com.velocity.Bean;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JoinJobBean {
    private String jobName;

    private String jobType;

    private List<SchemaBean> schemaList;

    private List<JdbcSourceParam> jdbcSourceParamList;

    private List<JdbcSourceInputFormat> jdbcSourceInputFormatList;

    private List<ImpalaSource> impalaSourceList;

    private List<JoinOperatorBean> joinOperatorList;

    private List<MapOperatorBean> mapOperatorList;

    private JdbcSinkBean jdbcSinkBean;

    private ImpalaSinkBean impalaSinkBean;

    private List<StreamDef> streamList;


}
