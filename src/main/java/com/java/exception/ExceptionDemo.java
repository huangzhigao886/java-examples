package com.java.exception;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Auther: huangzhigao
 * @Date: 2019/11/28
 * @Description:
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            Class.forName("com.s.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("open() failed:"+e.getMessage(),e);
        }
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306","root","123");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(""+e.getMessage(),e);
        }
    }
}
