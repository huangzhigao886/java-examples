package com.dto;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/17
 * @Description:
 */
@Data
public class JdbcSourceDto {
    private String id;
    private String className;
    //ref:sourceInfo连接信息，ref:schema schema信息
    private List<String> constructorArgs;
}
