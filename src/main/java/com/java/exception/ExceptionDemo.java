package com.java.exception;

import org.junit.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;

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
            throw new IllegalArgumentException("open() failed:" + e.getMessage(), e);
        }
        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "123");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("" + e.getMessage(), e);
        }
    }


    @Test
    public void test() {
        Hashtable<String, String> table = new Hashtable<String, String>();
        table.put("ss", "1");
        table.put("dd", "o");
        table.put("ss", "2");
        for (Map.Entry<String, String> entry : table.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        int v = 1;
        Integer.valueOf("12");
    }
}
