package com.modelJson.enums;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: huangzhigao
 * @Date: 2020/4/16
 * @Description: FlinkJson的常用量
 */
public interface FlinkConstant {

    /**
     * 改变数据结构的算子
     */
    List<String> changeStructOperatorList = Stream.of("operator_add", "operator_select", "operator_aggregation",
            "operator_groovy", "operator_leftJoin", "operator_innerJoin", "operator_union", "operator_difference")
            .collect(Collectors.toList());

    String REDIS = "REDIS";

    String HDFS = "HDFS";

    String TRUE = "TRUE";

    String FALSE = "FALSE";

    /**
     * 数据库的类型
     */
    List<String> JDBC_TYPE = Stream.of("MYSQL", "IMPALA", "ORACLE", "SQLSQERVER", "PGSQL").collect(Collectors.toList());

    String SOURCE = "SOURCE";

    String MONGODBPARAM_CLASS = "cn.encdata.cloud.dataengine.core.sources.batch.mongodb.MongoDbSourceInputParam";

    String JDBCPARAM_CLASS = "cn.encdata.cloud.dataengine.core.jdbc.JdbcSourceInputParam";

    String FTPPARAM_CLASS = "cn.encdata.cloud.dataengine.core.sources.batch.ftp.FtpSourceParam";

    String MONGODB = "mongodb";

//    String
}
