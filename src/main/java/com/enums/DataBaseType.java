package com.enums;


import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库枚举
 */
@Slf4j
public enum DataBaseType {
    MySql("mysql", "com.mysql.jdbc.Driver"),
    Oracle("oracle", "oracle.jdbc.OracleDriver"),
    PostgreSQL("postgresql", "org.postgresql.Driver");


    private static Pattern mysqlPattern = Pattern.compile("jdbc:mysql://(.+):\\d+/.+");
    private static Pattern oraclePattern = Pattern.compile("jdbc:oracle:thin:@(.+):\\d+:.+");
    private String typeName;
    private String driverClassName;

    /**
     * 构造函数
     * @param typeName 数据库类型
     * @param driverClassName 驱动类名称
     */
    DataBaseType(String typeName, String driverClassName) {
        this.typeName = typeName;
        this.driverClassName = driverClassName;
    }

    /**
     * 根据库名包裹table名称,防止有关键字段
     * @param tableName
     * @return
     */
    public String quoteTableName(String tableName) {
        String result = tableName;

        switch (this) {
            case MySql:
                result = "`" + tableName.replace("`", "``") + "`";
                break;
            case Oracle:
                break;
            case PostgreSQL:
                break;
            default:
                log.warn("unsupported database type");
                break;

        }

        return result;
    }

    /**
     * 注意：目前只实现了从 mysql/oracle 中识别出ip 信息.未识别到则返回 null.
     */
    public static String parseIpFromJdbcUrl(String jdbcUrl) {
        Matcher mysql = mysqlPattern.matcher(jdbcUrl);
        if (mysql.matches()) {
            return mysql.group(1);
        }
        Matcher oracle = oraclePattern.matcher(jdbcUrl);
        if (oracle.matches()) {
            return oracle.group(1);
        }
        return null;
    }

    public String getDriverClassName() {
        return this.driverClassName;
    }


    public String getTypeName() {
        return typeName;
    }

}
