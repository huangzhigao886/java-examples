package com.velocity.Bean;

import lombok.Data;

import java.util.List;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/18
 * @Description:
 */
@Data
public class JdbcSourceParam {
    private String id;
    private String className;
    private List<Object> constructArgsList;
}
