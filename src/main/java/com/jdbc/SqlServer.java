package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/12
 * @Description:
 */
public class SqlServer {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driverName);
        String dbURL = "jdbc:sqlserver://url:1433;databaseName=test";
        String userName = "test";
        String password = "123456";
        Connection conn = DriverManager.getConnection(dbURL, userName, password);
//        String sql = "select * from wyt.access_task_td";
//        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1));
//        }
//        System.out.println(conn);
    }
}
