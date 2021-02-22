package com.jdbc;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.*;

/**
 * @Auther: huangzhigao
 * @Date: 2019/12/3
 * @Description:
 */

public class OracleTest {
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class.forName("oracle.jdbc.OracleDriver");
//        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@url.146.14:1521:orcl", "test", "123456");
////        ResultSet resultSet = conn.prepareStatement("select * from TEST.\"user_detail\"").executeQuery();
////        System.out.println(resultSet);
//
//        System.out.println(conn);
//    }

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@url.146.14:1521:orcl", "test", "Bocom_123");
        return conn;
    }

    @Test
    public void testPartition() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//url.146.84:1521/orcl", "test", "123456");
        ResultSet resultSet = conn.createStatement().executeQuery("select ID,XINGMING,ADDRESS,AGE,SCORE,IDCARD,PHONE,MAIL,POSTCODE,TIME1,TIMEEND from TEST.A_ZLTEST01");

//        conn.createStatement().executeQuery("select * from \"TEST\".\"YEAR_PARTITION\" partition(SP3)");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1));

            System.out.println(resultSet.getObject(2));
            System.out.println(resultSet.getObject("AGE"));
            System.out.println(resultSet.getObject("AGE"));
            System.out.println(resultSet.getObject(3));


//            System.out.println(resultSet.getObject(1));
        }
    }


    @Test
    public void testGetData() throws SQLException, ClassNotFoundException {
        Connection conn = getConn();
        ResultSet resultSet = conn.createStatement().executeQuery("select * from \"TEST\".\"YEAR_PARTITION\" partition(sp1) where INVOICE_NO > 12");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(1));
            System.out.println(resultSet.getObject(2));
        }
    }


    @Test
    public void insertData1() throws SQLException, ClassNotFoundException, IOException {
        Connection conn = getConn();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("insert into \"TEST\".\"BIGDATA1\" (pic) values(?)");
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder.toString());
        BufferedInputStream in = null;
        for (int j = 0; j < 3000000; j++) {
            in = FileUtil.getInputStream("D://1.jpg");
            preparedStatement.setBinaryStream(1, in);
            preparedStatement.addBatch();
            if (j % 1000 == 0 && j != 0) {
                System.out.println("执行了" + j);
                preparedStatement.executeBatch();
            }
        }
        conn.commit();
        conn.close();

    }


    @Test
    public void insertDataToPartition1() throws SQLException, ClassNotFoundException {
        Connection conn = getConn();
        String sql = "insert into \"TEST\".\"YEAR_PARTITION\"(INVOICE_NO,SALE_YEAR,SALE_MONTH,SALE_DAY) values (?,?,?,?)";
        int[] year = {2016, 2017};
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 70300000; i < 70400000; i++) {
            int j = i % 2;
            preparedStatement.setInt(1, i);
            preparedStatement.setInt(2, year[j]);
            preparedStatement.setInt(3, i);
            preparedStatement.setInt(4, i);
            preparedStatement.addBatch();
            if (i % 5000 == 0) {
                preparedStatement.executeBatch();
            }
        }
        preparedStatement.executeBatch();
        conn.commit();
        conn.close();
    }

    @Test
    public void test1() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table bigData1 (id double,");
        for (int i = 0; i < 60; i++) {
            stringBuilder.append("lable" + i + " string(400)");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
    }


    @Test
    public void insertDataToPartition() throws SQLException, ClassNotFoundException {
        Connection conn = getConn();
        String sql = "insert into \"TEST\".\"YEAR_PARTITION\"(INVOICE_NO,SALE_YEAR,SALE_MONTH,SALE_DAY) values (?,?,?,?)";
        int[] year = {2010, 2011, 2012, 2013, 2014, 2015, 2016};
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        for (int i = 50100000; i < 50200000; i++) {
            int j = i % 6;
            preparedStatement.setInt(1, i);
            preparedStatement.setInt(2, year[j]);
            preparedStatement.setInt(3, i);
            preparedStatement.setInt(4, i);
            preparedStatement.addBatch();
            if (i % 5000 == 0) {
                preparedStatement.executeBatch();
            }
        }
        preparedStatement.executeBatch();
        conn.commit();
        conn.close();
    }
}
