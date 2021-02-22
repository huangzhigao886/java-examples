package com.md5;

/**
 * @Auther: huangzhigao
 * @Date: 2020/7/6
 * @Description:
 */
public class Demo1 {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("create table test_2000W ( id int,");
        for(int i = 0;i<60;i++){
            stringBuilder.append("label"+i+" varchar(255)");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(")");
        System.out.println(stringBuilder.toString());
    }
}
