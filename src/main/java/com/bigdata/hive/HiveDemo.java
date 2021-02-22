package com.bigdata.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: huangzhigao
 * @Date: 2020/10/23
 * @Description:
 */
public class HiveDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection connection = DriverManager.getConnection("jdbc:hive2://cluster-node-1:10000/default;principal=ossuser/cluster-node-2@HADOOP.COM");
        System.out.println(connection);

    }
}
