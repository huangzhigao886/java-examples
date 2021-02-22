package com.dto;


import com.enums.DataBaseType;
import lombok.Data;

import java.io.Serializable;

/**
 * jdbc连接参数
 *
 * @auth:qiss
 * @since DataEngine 1.0
 */
@Data
public class JdbcSourceInputParam implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4397814378782622781L;
    /**
     * 数据库名称
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 数据库标识
     * MySql
     * Oracle
     * PostgreSQL
     * sqlServer
     */
    private DataBaseType dbType;
    /**
     * 数据库连接地址
     */
    private String dbURL;
    /**
     * 表名
     */
    private String tables;

    /**
     * oracle schema
     */
    private String schema;
    /**
     * 增量字段
     */
    private String incFiled;

    /**
     * 增量字段起始值
     */
    private Object incFiledValue;


    /**
     * 构造函数,兼容之前的方式
     *
     * @param username
     * @param password
     * @param dbType
     * @param dbURL
     * @param tables
     * @param schema
     * @param incFiled
     * @param incFiledValue
     */
    public JdbcSourceInputParam(String username, String password, DataBaseType dbType, String dbURL, String tables, String schema, String incFiled, Object incFiledValue) {
        this.username = username;
        this.password = password;
        this.dbType = dbType;
        this.dbURL = dbURL;
        this.tables = tables;
        this.schema = schema;
        this.incFiled = incFiled;
        this.incFiledValue = incFiledValue;
    }
}
