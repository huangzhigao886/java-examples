package com.util;

/**
 * @description: 插入大量数据到mysql
 * @author: hzg
 * @create: 2019-09-11 11:01
 **/

public class InsertDateToMysql {

    public String preparedInsert(StringBuilder stringBuilder){
        stringBuilder = new StringBuilder("insert into table1 (NAME,ID) values ");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        long edd = System.currentTimeMillis();
        System.out.println(edd-begin);
    }
}
