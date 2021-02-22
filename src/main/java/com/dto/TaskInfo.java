package com.dto;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/17
 * @Description:
 */
@Data
public class TaskInfo {

    private String jobName;
    private String jobType;
    private List<Schema> schemaList;

    private List<JdbcDto> jdbcSourParam;

    //mysql的输入源
    private List<JdbcSourceDto> jdbcSourceList;

    private List impalaSourceList;
}
