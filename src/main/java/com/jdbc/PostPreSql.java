package com.jdbc;

import java.sql.*;

public class PostPreSql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://url.146.86:5432/zhy";
        String username = "postgres";
        String password = "postgres";

        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "select * from demo_schema.verify0314";
        PreparedStatement statement = connection.prepareStatement(sql);
//        statement.setint(1,String.valueOf(1));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String field1 = resultSet.getString(5);
            String field2 = resultSet.getString(6);
            System.out.println(field1.length());
            System.out.println(field2.length());

        }

    }
}
