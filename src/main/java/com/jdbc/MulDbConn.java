package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 多数据库连接
 */
public class MulDbConn {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","123456");
        Connection conn = DriverManager.getConnection("jdbc:mysql://url.112.93:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8","root","Bocom_123");
        ResultSet resultSet = conn.prepareStatement("select * from test.b").executeQuery();
//        String sql = "select tt.name,tt.ID,t2.id from flink.test as tt ,flinkjson.a as t2";
//        ResultSet rs = conn.prepareStatement(sql).executeQuery();
//        while(rs.next()){
//            System.out.print(rs.getString(1)+"\t");
//            System.out.print(rs.getString(2)+"\t");
//            System.out.print(rs.getString(3)+"\t");
//            System.out.println();
//        }
    }
}
