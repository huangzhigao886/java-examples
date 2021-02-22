package com.util;

import java.sql.*;

/**
 * @description: Pre的使用
 * @author: hzg
 * @create: 2019-09-17 12:08
 **/

public class JdbcPre {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:mysql://url:3306/enc_discard_Service?rewriteBatchedStatements=true";
//        String user = "bocom";
//        String password = "pass";
        String url = "jdbc:mysql://localhost:3306/flink?rewriteBatchedStatements=true";
        String user = "root";
        String password = "123456";
        //建立数据库连接
        Connection conn = DriverManager.getConnection(url, user, password);
//        System.out.println(conn);
        String sql = "insert into bbb (`id`,`name`) values (?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        for(int i = 6 ;i<50000;i++){
            statement.setInt(1,i);
            statement.setString(2,"huang"+i);
            statement.addBatch();
            if( i% 1000==0){
                statement.executeBatch();
            }
        }
        statement.executeBatch();
//
        statement.close();
        conn.close();
//        System.out.println(conn);
//        String sql = "insert into test12 (`date`,`id`) values(1,2) ";
//        String sql1 = "select * from datatype where binary `b` = 'aa' and binary `a` ='2019-09-18 13:56:37.000000' and binary c = 1 and binary d ='2019-09-18' " +
//                "and binary `e` = '13' and binary `f` = '13' and binary `h` = '13' ";
//        PreparedStatement ps = conn.prepareStatement(sql1);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()){
//            System.out.println(rs.getObject(1));
//            System.out.println(rs.getObject(2));
//            System.out.println(rs.getObject(3));
//            System.out.println(rs.getObject(4));
//            System.out.println(rs.getObject(5));
//            System.out.println(rs.getObject(6));
//            System.out.println(rs.getObject(7));
//            System.out.println(rs.getObject(8));
//        }

//        String sql = "insert into million (`id`,`name`) values (?,?)";
//        PreparedStatement pre = conn.prepareStatement(sql);
//
//        for(int i = 0;i<2000000;i++){
//            pre.setInt(1,i);
//            pre.setString(2,"name");
//            pre.addBatch();
//            if(i % 500==0){
//                long startTime = System.currentTimeMillis();
//                pre.executeBatch();
//                long stopTime = System.currentTimeMillis();
//                long cost = stopTime-startTime;
//                System.out.println("500条耗时:"+cost);
//            }

//        String sql = "insert into time_test (`birthday`) values ('2014-12-11 13:22:11')";
//        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        preparedStatement.execute();

    }





    }


